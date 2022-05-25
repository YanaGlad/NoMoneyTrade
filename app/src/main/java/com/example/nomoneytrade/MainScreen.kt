package com.example.nomoneytrade

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add new product",
                    )
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