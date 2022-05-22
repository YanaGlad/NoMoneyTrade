package com.example.nomoneytrade

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nomoneytrade.auth.AuthSignInScreen
import com.example.nomoneytrade.auth.AuthSignUpScreen
import com.example.nomoneytrade.showcase.ShowcaseScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = SIGN_IN_SCREEN) {  //temp
        composable(SIGN_IN_SCREEN) { AuthSignInScreen(navController, hiltViewModel()).ShowScreen() }
        composable(SHOWCASE_SCREEN) { ShowcaseScreen(navController, hiltViewModel()).ShowScreen() }
        composable(SIGN_UP_SCREEN) { AuthSignUpScreen(navController, hiltViewModel()).ShowScreen() }
    }
}

//auth
const val SIGN_IN_SCREEN = "auth_screen"
const val SIGN_UP_SCREEN = "sign_up_screen"

//showcase
const val SHOWCASE_SCREEN = "showcase_screen"