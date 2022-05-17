package com.example.nomoneytrade.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nomoneytrade.R
import com.example.nomoneytrade.ui.utils.UiUtilsTextField


@Composable
fun AuthorizeScreen() {

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
            text = "NoMoneyTrade", //TODO replace with data from string.xml
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "Authentication", //TODO replace with data from string.xml
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        )

        UiUtilsTextField(label = "email", padding = 15)
        UiUtilsTextField(label = "password", padding = 15)

        ExtendedFloatingActionButton(
            onClick = {
                //TODO navigation to sign up
            },
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 15.dp)
                .wrapContentWidth()
                .align(CenterHorizontally),

            backgroundColor = MaterialTheme.colors.primary,
            text = {
                Text(
                    text = "Sign up",
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .weight(5f)
                        .wrapContentWidth(),
                )
            }
        )
    }
}