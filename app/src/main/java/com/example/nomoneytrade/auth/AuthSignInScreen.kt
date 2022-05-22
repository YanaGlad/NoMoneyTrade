package com.example.nomoneytrade.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.nomoneytrade.R
import com.example.nomoneytrade.SHOWCASE_SCREEN
import com.example.nomoneytrade.SIGN_UP_SCREEN
import com.example.nomoneytrade.mvi.effect.AuthEffect
import com.example.nomoneytrade.mvi.event.AuthEvent
import com.example.nomoneytrade.ui.utils.ComposeScreen
import com.example.nomoneytrade.ui.utils.UiUtilsExtendedFloatingButton
import com.example.nomoneytrade.ui.utils.UiUtilsTextField
import kotlinx.coroutines.launch


class AuthSignInScreen(private val navController: NavController, private val viewModel: AuthViewModel) :
    ComposeScreen<AuthViewModel>(navController, viewModel) {

    override val ON_CLOSE_DESTINATION: String = SHOWCASE_SCREEN
    override val showCloseButton: Boolean = true
    override val showBackButton: Boolean = false
    override val ON_BACK_DESTINATION: String? = null

    @Composable
    override fun Screen() {
        Column {
            Toolbar()
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_handshake),
                modifier = Modifier
                    .wrapContentWidth()
                    .height(170.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp),
                contentDescription = "App Icon",
            )
            Text(
                text = stringResource(R.string.app_name),
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = stringResource(R.string.exchange_service),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
            )

            Text(
                text = stringResource(R.string.authentication),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
            )

            var emailText by remember { mutableStateOf("") }
            UiUtilsTextField(label = stringResource(R.string.email), padding = 15, text = emailText) { text ->
                emailText = text
            }

            var passwordText by remember { mutableStateOf("") }
            UiUtilsTextField(label = stringResource(R.string.password), padding = 15, text = passwordText) { text ->
                passwordText = text
            }

            UiUtilsExtendedFloatingButton(stringResource(R.string.sign_in)) {
                viewModel.run {
                    viewModelScope.launch { //TODO launch scopes in viewModel class
                        singIn(login = emailText, password = passwordText)
                    }
                }
            }

            Text(
                text = stringResource(R.string.no_account_yet),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = true) {
                        viewModel.navigateToSignUp()
                    },
            )
        }
    }

    @Composable
    override fun ObserveViewModel() {

        val eventState = viewModel.event.collectAsState()

        when (val event = eventState.value) {
            is AuthEvent.Error -> {}
            is AuthEvent.Loading -> {}
            is AuthEvent.Success -> {

            }
            is AuthEvent.FailedToLogin -> {}
        }

        val effectState = viewModel.effect.collectAsState()

        when (val effect = effectState.value) {
            is AuthEffect.NavigateShowcase -> {

            }
            is AuthEffect.NavigateSignUp -> {
                navController.navigate(SIGN_UP_SCREEN)
                viewModel.effect.value = AuthEffect.None
            }
            is AuthEffect.None -> {

            }
        }
    }
}
