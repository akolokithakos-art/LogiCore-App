package com.example.logicore.features.dispatch.presentation

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.address.domain.AddressGeocoder
import com.example.logicore.features.address.domain.model.DetailedAddress
import com.example.logicore.features.fleet.data.local.DriverDao
import com.example.logicore.features.fleet.data.local.DriverEntity
import com.example.logicore.features.firebase.repository.FirebaseRouteRepository
import com.example.logicore.features.firebase.model.RemoteRoutePlan
import com.example.logicore.routing.engine.RouteResult
import com.example.logicore.routing.engine.RoutingEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.InputStream

data class LegInfo(
    val from: String,
    val to: String,
    val result: RouteResult
)

class ManualRoutingViewModel(
    application: Application,
    private val geocoder: AddressGeocoder,
    private val routingEngine: RoutingEngine,
    private val driverDao: DriverDao,
    private val routeRepository: FirebaseRouteRepository
) : AndroidViewModel(application) {

    private val _addresses = MutableStateFlow<List<DetailedAddress>>(emptyList())
    val addresses: StateFlow<List<DetailedAddress>> = _addresses

    private val _routeLegs = MutableStateFlow<List<LegInfo>>(emptyList())
    val routeLegs: StateFlow<List<LegInfo>> = _routeLegs

    private val _isCalculating = MutableStateFlow(false)
    val isCalculating: StateFlow<Boolean> = _isCalculating

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _drivers = MutableStateFlow<List<DriverEntity>>(emptyList())
    val drivers: StateFlow<List<DriverEntity>> = _drivers

    init {
        loadDrivers()
    }

    private fun loadDrivers() {
        viewModelScope.launch {
            var list = driverDao.getAll("tenant-1")
            if (list.isEmpty()) {
                val mockDrivers = listOf(
                    DriverEntity("driver-1", "tenant-1", "D001", "Aris", "Papadopoulos", "123", null, "LIC001"),
                    DriverEntity("driver-2", "tenant-1", "D002", "Eleni", "Georgiou", "456", null, "LIC002")
                )
                mockDrivers.forEach { driverDao.upsert(it) }
                list = mockDrivers
            }
            _drivers.value = list
        }
    }

    fun addAddress(address: DetailedAddress) {
        if (address.street.isNotBlank()) {
            _addresses.value = _addresses.value + address
            _error.value = null
        }
    }

    fun removeAddress(address: DetailedAddress) {
        _addresses.value = _addresses.value - address
    }

    fun clearError() {
        _error.value = null
    }

    fun importFromFile(uri: Uri) {
        viewModelScope.launch {
            val contentResolver = getApplication<Application>().contentResolver
            val fileName = uri.path?.lowercase() ?: ""
            
            withContext(Dispatchers.IO) {
                try {
                    val imported = when {
                        fileName.endsWith(".xlsx") || fileName.endsWith(".xls") -> parseExcel(uri)
                        fileName.endsWith(".docx") -> parseWord(uri)
                        fileName.endsWith(".csv") -> parseCsv(uri)
                        else -> parseGenericText(uri)
                    }
                    if (imported.isEmpty()) {
                        _error.value = "Δεν βρέθηκαν διευθύνσεις στο αρχείο."
                    } else {
                        _addresses.value = _addresses.value + imported
                        _error.value = null
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _error.value = "Σφάλμα κατά την ανάγνωση του αρχείου: ${e.localizedMessage}"
                }
            }
        }
    }

    private fun parseExcel(uri: Uri): List<DetailedAddress> {
        val list = mutableListOf<DetailedAddress>()
        getApplication<Application>().contentResolver.openInputStream(uri)?.use { stream ->
            val workbook = WorkbookFactory.create(stream)
            val sheet = workbook.getSheetAt(0)
            for (row in sheet) {
                val street = row.getCell(0)?.toString() ?: ""
                val area = row.getCell(1)?.toString() ?: ""
                if (street.isNotBlank()) {
                    list.add(DetailedAddress(street = street, area = area))
                }
            }
        }
        return list
    }

    private fun parseWord(uri: Uri): List<DetailedAddress> {
        val list = mutableListOf<DetailedAddress>()
        getApplication<Application>().contentResolver.openInputStream(uri)?.use { stream ->
            val doc = XWPFDocument(stream)
            for (para in doc.paragraphs) {
                val line = para.text
                if (line.isNotBlank()) {
                    list.add(DetailedAddress(street = line, area = ""))
                }
            }
        }
        return list
    }

    private fun parseCsv(uri: Uri): List<DetailedAddress> {
        val list = mutableListOf<DetailedAddress>()
        getApplication<Application>().contentResolver.openInputStream(uri)?.bufferedReader()?.use { reader ->
            reader.lineSequence().forEach { line ->
                val parts = line.split(",")
                if (parts.isNotEmpty() && parts[0].isNotBlank()) {
                    list.add(DetailedAddress(street = parts[0], area = parts.getOrNull(1) ?: ""))
                }
            }
        }
        return list
    }

    private fun parseGenericText(uri: Uri): List<DetailedAddress> {
        val list = mutableListOf<DetailedAddress>()
        getApplication<Application>().contentResolver.openInputStream(uri)?.bufferedReader()?.use { reader ->
            reader.lineSequence().forEach { line ->
                if (line.isNotBlank()) {
                    list.add(DetailedAddress(street = line, area = ""))
                }
            }
        }
        return list
    }

    fun createRoute() {
        viewModelScope.launch {
            _isCalculating.value = true
            _error.value = null
            val currentList = _addresses.value
            if (currentList.size < 2) {
                _isCalculating.value = false
                _error.value = "Απαιτούνται τουλάχιστον 2 διευθύνσεις για δρομολόγιο."
                return@launch
            }

            val legs = mutableListOf<LegInfo>()
            
            try {
                for (i in 0 until currentList.size - 1) {
                    val fromAddr = currentList[i]
                    val toAddr = currentList[i + 1]
                    
                    val fromPoint = geocoder.geocode(fromAddr.street, fromAddr.number, fromAddr.area, fromAddr.postalCode)
                    val toPoint = geocoder.geocode(toAddr.street, toAddr.number, toAddr.area, toAddr.postalCode)
                    
                    if (fromPoint != null && toPoint != null) {
                        val result = routingEngine.getRoute(
                            fromPoint.latitude, fromPoint.longitude,
                            toPoint.latitude, toPoint.longitude
                        )
                        legs.add(LegInfo(fromAddr.toString(), toAddr.toString(), result))
                    } else {
                        val failed = if (fromPoint == null) fromAddr else toAddr
                        _error.value = "Αποτυχία εύρεσης συντεταγμένων για: $failed"
                        _isCalculating.value = false
                        return@launch
                    }
                }
                
                if (legs.isEmpty()) {
                    _error.value = "Δεν κατέστη δυνατός ο υπολογισμός δρομολογίου."
                } else {
                    _routeLegs.value = legs
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Σφάλμα κατά τον υπολογισμό: ${e.localizedMessage}"
            } finally {
                _isCalculating.value = false
            }
        }
    }

    fun dispatchRoute(driverId: String) {
        viewModelScope.launch {
            try {
                val plan = RemoteRoutePlan(
                    tenantId = "tenant-1",
                    driverId = driverId,
                    routePoints = _addresses.value.map { it.toString() }
                )
                routeRepository.saveRoute(plan)
                _error.value = "Το δρομολόγιο εστάλη επιτυχώς!"
            } catch (e: Exception) {
                _error.value = "Αποτυχία αποστολής: ${e.localizedMessage}"
            }
        }
    }
}
