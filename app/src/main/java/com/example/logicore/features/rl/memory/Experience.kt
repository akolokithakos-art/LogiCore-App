package com.example.logicore.features.rl.memory

import com.example.logicore.features.rl.state.DispatchState
import com.example.logicore.features.rl.action.DispatchAction

data class Experience(

    val state: DispatchState,
    val action: DispatchAction,
    val reward: Double,
    val nextState: DispatchState
)