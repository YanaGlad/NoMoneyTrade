package com.example.nomoneytrade

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nomoneytrade.auth.AuthorizeScreen
import com.example.nomoneytrade.products.ShowcaseScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = AUTH_SCREEN) {  //temp
        composable(AUTH_SCREEN) { AuthorizeScreen(navController) }
        composable(SHOWCASE_SCREEN) { ShowcaseScreen(navController) }
    }
}

const val AUTH_SCREEN = "auth_screen"
const val SHOWCASE_SCREEN = "showcase_screen"