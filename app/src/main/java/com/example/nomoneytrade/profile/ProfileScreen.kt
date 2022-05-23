package com.example.nomoneytrade.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nomoneytrade.R

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.stub), //TODO load profile photo via url photoState.value
            modifier = Modifier
                .wrapContentWidth()
                .height(200.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp, start = 30.dp, end = 30.dp),
            contentDescription = "App Icon",
            contentScale = ContentScale.Crop,
        )
        Text(
                text = "Albert Einstein",
        fontSize = 28.sp,
        modifier = Modifier
            .fillMaxWidth()
            .align(CenterHorizontally)
            .padding(top = 8.dp),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        )

        Text(
                text = "Voronezh",
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp),
                textAlign = TextAlign.Start,
        )

        Text(
            text = "+ 7-930-777-77-77",
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp),
            textAlign = TextAlign.Start,
        )


        // Button active offers -> (from compose demo?)
        // quit button with door in the right top corner
    }
}