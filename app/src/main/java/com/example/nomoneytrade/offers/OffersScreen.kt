package com.example.nomoneytrade.offers

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.nomoneytrade.profile.ProfileViewModel
import com.example.nomoneytrade.ui.utils.ComposeScreen

class OffersScreen(navController: NavController, private val viewModel: ProfileViewModel) : ComposeScreen<ProfileViewModel>(navController, viewModel) {
    override val ON_CLOSE_DESTINATION: String? = null
    override val ON_BACK_DESTINATION: String? = null
    override val showCloseButton: Boolean = false
    override val showBackButton: Boolean = false

    @Composable
    override fun Screen() {

    }
}
