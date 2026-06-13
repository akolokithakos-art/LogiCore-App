package com.example.logicore.features.dashboard.state

import com.example.logicore.features.dispatch.realtime.LiveDispatchState
import com.example.logicore.features.sales.data.local.SaleEntity

data class ControlCenterState(

    val vehicles: List<LiveDispatchState> = emptyList(),

    val sales: List<SaleEntity> = emptyList(),

    val alerts: List<String> = emptyList(),

    val aiDecisions: List<String> = emptyList()
)