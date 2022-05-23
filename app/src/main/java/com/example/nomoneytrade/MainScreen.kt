package com.example.nomoneytrade

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nomoneytrade.showcase.ShowcaseScreen
import com.example.nomoneytrade.ui.utils.BottomNavigationBar

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) {

        //На каждый экран ивент
        // По клику на кнопку перехода летит навигационный ивент
        ShowcaseScreen(navController, hiltViewModel()).ShowScreen()
    }
}