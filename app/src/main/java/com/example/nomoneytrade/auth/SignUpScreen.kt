package com.example.nomoneytrade.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nomoneytrade.R

@Composable
fun SignUpScreen(navController: NavController) {
    Column {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_camera),
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .padding(top = 20.dp),
            contentDescription = "profile photo",
        )
    }
}