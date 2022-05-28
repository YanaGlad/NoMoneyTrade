package com.example.nomoneytrade.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.nomoneytrade.R
import com.example.nomoneytrade.SIGN_IN_SCREEN
import com.example.nomoneytrade.mvi.event.ProfileEvent
import com.example.nomoneytrade.ui.utils.UiUtilsLoadingFullScreen
import com.example.nomoneytrade.ui.utils.UiUtilsToolbarButton

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel) {
    BackHandler {}
    UiUtilsLoadingFullScreen()
    val profileState = viewModel.profile.collectAsState()

    when (val event = profileState.value) {
        ProfileEvent.Error -> {}
        ProfileEvent.Loading -> UiUtilsLoadingFullScreen()
        is ProfileEvent.Success -> {
            Profile(navController, event)
        }
    }

}

@Composable
private fun Profile(
    navController: NavController,
    profileState: ProfileEvent.Success,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {

        UiUtilsToolbarButton(navController = navController, destination = SIGN_IN_SCREEN, icon = R.drawable.ic_exit)

        if (profileState.user.iconUrl != "") {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = profileState.user.iconUrl)
                        .allowHardware(false)
                        .build()
                ),
                modifier = Modifier
                    .size(250.dp)
                    .align(CenterHorizontally)
                    .offset(y = (-10).dp)
                    .clip(CircleShape),
                contentDescription = "profile photo",
                contentScale = ContentScale.Crop,
            )
        } else {
            Box(modifier = Modifier.size(250.dp).clip(CircleShape).align(CenterHorizontally)) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_circle_backg),
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Center)
                        .clickable {
                            //CHOOSE PHOTO
                        }
                        .padding(top = 20.dp),
                    contentDescription = "profile photo stub",
                    contentScale = ContentScale.Crop,
                )
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_camera),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .align(Center)
                        .padding(top = 10.dp, start = 5.dp),
                    contentDescription = "profile photo sub camera",
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Text(
            text = profileState.user.username,
            fontSize = 28.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .padding(top = 8.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
//        Text(
//            text = "(${profileState.user.fio})",
//            fontSize = 16.sp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(CenterHorizontally)
//                .padding(top = 8.dp),
//            textAlign = TextAlign.Center,
//        )

        DefaultTextField("${stringResource(R.string.email)}: ${profileState.user.email}")

        DefaultTextField("$${stringResource(R.string.phone_number)}: ${profileState.user.phoneNumber}")

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