package com.example.nomoneytrade.ui.utils

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

abstract class ComposeScreen<VM> constructor(private val navController: NavController, private val viewModel: VM) {
    abstract val ON_CLOSE_DESTINATION: String?
    abstract val ON_BACK_DESTINATION: String?
    abstract val showCloseButton: Boolean
    abstract val showBackButton: Boolean

    @Composable
    fun ShowScreen() {
        ObserveViewModel()
        BackHandler()
        Screen()
    }

    @Composable
    protected fun ColumnScope.defaultScreen(custom: () -> Unit) {
        ON_CLOSE_DESTINATION?.let { UiUtilsCloseButton(navController = navController, destination = it) }
        custom()
    }

    @Composable
    protected fun ColumnScope.Toolbar() {
        if (showCloseButton) ON_CLOSE_DESTINATION?.let { UiUtilsCloseButton(navController = navController, destination = it) }
    }

    @Composable
    protected fun BackHandler() {
        BackHandler {
            ON_BACK_DESTINATION?.let { navController.navigate(it) }
        }
    }

    @Composable
    protected abstract fun Screen()

    @Composable
    protected abstract fun ObserveViewModel()
}