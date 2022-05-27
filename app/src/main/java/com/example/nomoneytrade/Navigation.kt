package com.example.nomoneytrade

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nomoneytrade.auth.AuthSignInScreen
import com.example.nomoneytrade.auth.AuthSignUpScreen
import com.example.nomoneytrade.offers.OffersScreen
import com.example.nomoneytrade.productView.ProductInfoScreen
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.onboarding.OnboardingScreen
import com.example.nomoneytrade.showcase.ShowcaseScreen
import com.example.nomoneytrade.suggest.SuggestChoiceScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = MAIN_SCREEN) {  //temp
        composable(MAIN_SCREEN) { MainScreen(navController) }
        //  composable(PROFILE_SCREEN) { ProfileScreen(navController, hiltViewModel()).ShowScreen() }
        composable(SIGN_IN_SCREEN) { AuthSignInScreen(navController, hiltViewModel())  }
        composable(SHOWCASE_SCREEN) { ShowcaseScreen(navController, hiltViewModel()).ShowScreen() }
        composable(SIGN_UP_SCREEN) { AuthSignUpScreen(navController, hiltViewModel()) }
        composable("$PRODUCT_INFO_SCREEN/{id}/{url}/{authorId}/{title}/{description}/{tags}/{extags}",
            arguments = listOf(
                navArgument("id") { defaultValue = -1L },
                navArgument("title") { defaultValue = "" },
                navArgument("description") { defaultValue = "" },
                navArgument("url") { defaultValue = "" },
                navArgument("authorId") { defaultValue = -1L },
                navArgument("tags") { defaultValue = "AAAAA" },
                navArgument("extags") { defaultValue = "AAAAA" },
            )
        ) {
            ProductInfoScreen(
                navController = navController,
                product = Product(
                    id = it.arguments?.getLong("id") ?: -1,
                    userId = it.arguments?.getLong("authorId") ?: -1,
                    title = it.arguments?.getString("title") ?: "",
                    imageUrl = it.arguments?.getString("url") ?: "",
                    description = it.arguments?.getString("description") ?: "",
                    favourites = false,
                    tags = listOf(),
                    exchangeTags = listOf()
                ),
                tags = it.arguments?.getString("tags") ?: "",
                extags = it.arguments?.getString("extags") ?: "",
                viewModel = hiltViewModel())
        }
        composable(OFFERS_SCREEN) { OffersScreen(navController, hiltViewModel()) }
        composable("$ONBOARDING_SCREEN/{title}/{description}/{drawable}") {
            OnboardingScreen(
                navController = navController,
                title = it.arguments?.getString("title") ?: "",
                description = it.arguments?.getString("description") ?: "",
                drawable = it.arguments?.getInt("drawable") ?: R.drawable.ic_exchange,
            )
        }
        composable("$SUGGEST_SCREEN/{tags}") {
            SuggestChoiceScreen(it.arguments?.getString("tags") ?: "", navController, hiltViewModel())
        }
    }
}

const val MAIN_SCREEN = "main"

//auth
const val SIGN_IN_SCREEN = "auth_screen"
const val SIGN_UP_SCREEN = "sign_up_screen"

//showcase
const val SHOWCASE_SCREEN = "showcase_screen"

const val ONBOARDING_SCREEN = "onboardinge_screen"

//product
const val PRODUCT_INFO_SCREEN = "product_info_screen"

//Bottom
const val PROFILE_SCREEN = "profile_screen"
const val OFFERS_SCREEN = "notifications_screen"
const val SUGGEST_SCREEN = "suggest_screen"

