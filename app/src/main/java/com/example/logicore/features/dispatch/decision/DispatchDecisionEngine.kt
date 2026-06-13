package com.example.logicore.features.dispatch.decision

class DispatchDecisionEngine {

    fun decide(context: DispatchContext): DispatchAction {

        // 1. CRITICAL STOCK ISSUE
        if (context.lowStockProducts > 3) {
            return DispatchAction.EmergencyReload(
                vehicleId = context.vehicleId,
                warehouseId = 1
            )
        }

        // 2. ROUTE DEVIATION
        if (context.routeDeviationMeters > 800) {
            return DispatchAction.Reroute(
                vehicleId = context.vehicleId,
                newRouteId = -1 // placeholder for route optimizer
            )
        }

        // 3. OVERLOADED WORKFLOW
        if (context.pendingOrders > 20) {
            return DispatchAction.StopAndValidate(
                vehicleId = context.vehicleId,
                reason = "Too many pending orders"
            )
        }

        // 4. DEFAULT
        return DispatchAction.ContinueRoute(
            vehicleId = context.vehicleId
        )
    }
}