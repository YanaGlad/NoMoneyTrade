package com.example.nomoneytrade.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.nomoneytrade.R
import com.example.nomoneytrade.SIGN_UP_SCREEN
import com.example.nomoneytrade.mvi.effect.AuthEffect
import com.example.nomoneytrade.mvi.event.AuthEvent
import com.example.nomoneytrade.ui.utils.UiUtilsTextField
import kotlinx.coroutines.flow.collect

@Composable
fun AuthorizeScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    observeViewModel(navController, viewModel)
    SetupUi(viewModel)
}

@Composable
private fun observeViewModel(navController: NavController, viewModel: AuthViewModel) {
    LaunchedEffect(viewModel) {
        viewModel.event.collect { state ->
            when (state) {
                AuthEvent.Error -> {}
                AuthEvent.Loading -> {}
                is AuthEvent.Success -> {

                }
                AuthEvent.FailedToLogin -> {}
            }
        }

        viewModel.effect.collect { state ->
            when (state) {
                AuthEffect.NavigateShowcase -> {

                }
                AuthEffect.NavigateSignUp -> {
                    navController.navigate(SIGN_UP_SCREEN)
                }
                AuthEffect.None -> {

                }
            }
        }
    }
}

@Composable
private fun SetupUi(viewModel: AuthViewModel) {

    Column {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_handshake),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(CenterHorizontally)
                .padding(top = 100.dp),
            contentDescription = "App Icon",
        )

        Text(
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = stringResource(R.string.exchange_service),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
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
                .padding(top = 10.dp),
        )

        var emailText by remember { mutableStateOf("") }
        UiUtilsTextField(label = stringResource(R.string.email), padding = 15) { text->
            emailText = text
        }

        var passwordText by remember { mutableStateOf("") }
        UiUtilsTextField(label = stringResource(R.string.password), padding = 15) { text ->
            passwordText = text
        }

        ExtendedFloatingActionButton(
            onClick = {
                viewModel.singIn(login = emailText, password = passwordText)
            },
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 15.dp)
                .wrapContentWidth()
                .align(CenterHorizontally),

            backgroundColor = MaterialTheme.colors.primary,
            text = {
                Text(
                    text = stringResource(R.string.sign_in),
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .weight(5f)
                        .wrapContentWidth(),
                )
            }
        )

        Text(
            text = stringResource(R.string.no_account_yet),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = true) {
                    viewModel.navigateToSignUp()
                },
        )
    }
}