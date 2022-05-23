package com.example.nomoneytrade

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nomoneytrade.mvi.event.BottomNavEvent
import com.example.nomoneytrade.offers.OffersScreen
import com.example.nomoneytrade.profile.ProfileScreen
import com.example.nomoneytrade.showcase.ShowcaseScreen
import com.example.nomoneytrade.ui.utils.BottomNavigationBar

@Composable
fun MainScreen(navController: NavController) {

    var eventState: BottomNavEvent by remember { mutableStateOf(BottomNavEvent.Showcase) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar() { event ->
                eventState = event
            }
        }
    ) {
        when (eventState) {
            BottomNavEvent.Offers -> OffersScreen(navController, hiltViewModel())
            BottomNavEvent.Profile -> ProfileScreen(navController, hiltViewModel())
            BottomNavEvent.Showcase -> ShowcaseScreen(navController, hiltViewModel()).ShowScreen()
        }
    }
}