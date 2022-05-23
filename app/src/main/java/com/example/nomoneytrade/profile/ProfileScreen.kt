package com.example.nomoneytrade.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nomoneytrade.R
import com.example.nomoneytrade.ui.utils.ComposeScreen

class ProfileScreen(navController: NavController, private val viewModel: ProfileViewModel) : ComposeScreen<ProfileViewModel>(navController, viewModel) {

    override val ON_CLOSE_DESTINATION: String? = null
    override val ON_BACK_DESTINATION: String? = null
    override val showCloseButton: Boolean = false
    override val showBackButton: Boolean = false

    @Composable
    override fun Screen() {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_profile), //TODO load profile photo via url photoState.value
                modifier = Modifier
                    .wrapContentWidth()
                    .height(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp),
                contentDescription = "App Icon",
            )
        }
    }
}