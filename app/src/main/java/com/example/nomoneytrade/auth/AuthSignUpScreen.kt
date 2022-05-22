package com.example.nomoneytrade.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nomoneytrade.R
import com.example.nomoneytrade.SHOWCASE_SCREEN
import com.example.nomoneytrade.SIGN_IN_SCREEN
import com.example.nomoneytrade.mvi.effect.AuthEffect
import com.example.nomoneytrade.mvi.event.AuthEvent
import com.example.nomoneytrade.ui.utils.ComposeScreen
import com.example.nomoneytrade.ui.utils.UiUtilsExtendedFloatingButton
import com.example.nomoneytrade.ui.utils.UiUtilsTextField


class AuthSignUpScreen(private val navController: NavController, private val viewModel: AuthViewModel) :
    ComposeScreen<AuthViewModel>(navController, viewModel) {

    override val ON_CLOSE_DESTINATION: String? = null
    override val ON_BACK_DESTINATION: String = SIGN_IN_SCREEN

    override val showCloseButton: Boolean = false
    override val showBackButton: Boolean = true

    @Composable
    override fun Screen() {
        var progressState by remember { mutableStateOf(false) }

        val eventState = viewModel.event.collectAsState()

        when (val event = eventState.value) {
            is AuthEvent.Error -> {}
            is AuthEvent.Loading -> {
                progressState = true
            }
            is AuthEvent.Success -> {
                progressState = false
                viewModel.navigate()
            }
            is AuthEvent.FailedToLogin -> {

            }
            AuthEvent.None -> {}
        }

        val effectState = viewModel.effect.collectAsState()

        when (val effect = effectState.value) {
            is AuthEffect.NavigateShowcase -> {

            }
            is AuthEffect.Navigate -> {
                navController.navigate(SHOWCASE_SCREEN)
                viewModel.effect.value = AuthEffect.None
            }
            is AuthEffect.None -> {
            }
        }

        Toolbar()
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())) {

            Box(modifier = Modifier
                .wrapContentWidth()
                .height(170.dp)
                .align(CenterHorizontally)
                .padding(top = 20.dp)) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_circle_backg),
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxHeight()
                        .align(Center)
                        .padding(top = 20.dp),
                    contentDescription = "profile photo",
                    contentScale = ContentScale.Crop,
                )
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_camera),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .align(Center)
                        .padding(top = 10.dp, start = 5.dp),
                    contentDescription = "profile photo",
                    contentScale = ContentScale.Crop,
                )
            }

            Text(
                text = stringResource(R.string.registration),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
            )


            var username by remember { mutableStateOf("") }
            UiUtilsTextField(label = stringResource(R.string.username), padding = 15, text = username) { text ->
                username = text
            }

            var email by remember { mutableStateOf("") }
            UiUtilsTextField(label = stringResource(R.string.email), padding = 15, text = email) { text ->
                email = text
            }

            var password by remember { mutableStateOf("") }
            UiUtilsTextField(label = stringResource(R.string.password), padding = 15, text = password) { text ->
                password = text
            }

            var confirmPassword by remember { mutableStateOf("") }
            UiUtilsTextField(label = stringResource(R.string.confirmPassword), padding = 15, text = confirmPassword) { text ->
                confirmPassword = text
            }

            UiUtilsExtendedFloatingButton(stringResource(R.string.sign_up), showProgress = progressState) {
                viewModel.signUpClick(username = username, password = password, email = email)
            }

            Text(
                text = stringResource(R.string.already_have_account),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = true) {
                        //to signIn
                    },
            )
        }
    }
}
