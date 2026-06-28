package com.example.logicore.features.procurement.rl

enum class ProcurementRLAction {


    BUY_AGGRESSIVE,   // over-order
    BUY_NORMAL,       // normal order
    WAIT,             // delay
    SWITCH_SUPPLIER   // change source


}
