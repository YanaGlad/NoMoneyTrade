package com.example.nomoneytrade.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.nomoneytrade.R
import com.example.nomoneytrade.SIGN_IN_SCREEN
import com.example.nomoneytrade.ui.utils.UiUtilsToolbarButton

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel) {
    BackHandler {}

    val profileState = viewModel.profile.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {

        UiUtilsToolbarButton(navController = navController, destination = SIGN_IN_SCREEN, icon = R.drawable.ic_exit)

        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = profileState.value.iconUrl)
                    .allowHardware(false)
                    .build()
            ),
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.CenterHorizontally)
                .offset(y = (-10).dp)
                .clip(CircleShape),
            contentDescription = "App Icon",
            contentScale = ContentScale.Crop,
        )
        Text(
            text = profileState.value.username,
            fontSize = 28.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .padding(top = 8.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "(${profileState.value.fio})",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
        )

        DefaultTextField(profileState.value.email)

        DefaultTextField(profileState.value.phoneNumber)

        //TODO возможность редактировать профиль, добавлять город, адрес
     //   DefaultTextField("${profileState.value.city}, ${profileState.value.address}")

//        Text(
//            text = "Active offers: ",
//            fontSize = 22.sp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 12.dp, start = 16.dp),
//            textAlign = TextAlign.Start,
//            fontWeight = FontWeight.Bold,
//        )

        Column(modifier = Modifier.fillMaxWidth()) {
        }
    }
}

@Composable
private fun DefaultTextField(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 16.dp),
        textAlign = TextAlign.Start,
    )
}