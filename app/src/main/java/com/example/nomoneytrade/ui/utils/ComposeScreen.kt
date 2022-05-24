package com.example.nomoneytrade.ui.utils

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.nomoneytrade.R

abstract class ComposeScreen<VM> constructor(private val navController: NavController, private val viewModel: VM) {
    abstract val ON_CLOSE_DESTINATION: String?
    abstract val ON_BACK_DESTINATION: String?
    abstract val showCloseButton: Boolean
    abstract val showBackButton: Boolean

    @Composable
    fun ShowScreen() {
        BackHandler()
        Screen()
    }

    @Composable
    protected fun ColumnScope.DefaultScreen(custom: () -> Unit) {
        ON_CLOSE_DESTINATION?.let { UiUtilsToolbarButton(navController = navController, destination = it, icon = R.drawable.ic_close) }
        custom()
    }

    @Composable
    protected fun Toolbar() {
        Row() {
            Column(Modifier
                .weight(1f)
                .wrapContentHeight()) {
                if (showBackButton) ON_BACK_DESTINATION?.let { UiUtilsToolbarButton(navController = navController, destination = it, icon = R.drawable.ic_back) }
            }
            Column(Modifier
                .weight(1f)
                .wrapContentHeight()) {
                if (showCloseButton) ON_CLOSE_DESTINATION?.let { UiUtilsToolbarButton(navController = navController, destination = it,  icon = R.drawable.ic_close) }
            }
        }
    }

    @Composable
    protected fun BackHandler() {
        BackHandler {
            ON_BACK_DESTINATION?.let { navController.navigate(it) }
        }
    }

    @Composable
    protected abstract fun Screen()
}