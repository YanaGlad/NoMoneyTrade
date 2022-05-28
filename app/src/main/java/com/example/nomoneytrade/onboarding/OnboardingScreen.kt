package com.example.nomoneytrade.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.nomoneytrade.MAIN_SCREEN
import com.example.nomoneytrade.R
import com.example.nomoneytrade.ui.utils.UiUtilsToolbarButton

@Composable
fun OnboardingScreen(navController: NavController, title: String, description: String) {
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        UiUtilsToolbarButton(navController, MAIN_SCREEN, R.drawable.ic_close)

        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            fontWeight = FontWeight.Bold,
        )
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_checkmark),
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .align(CenterHorizontally)
                .padding(top = 10.dp, start = 5.dp),
            contentDescription = "profile photo",
            contentScale = ContentScale.Crop,
        )
        Text(
            text = stringResource(R.string.now_meent),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
        )
    }
}