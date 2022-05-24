package com.example.nomoneytrade.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.nomoneytrade.R
import kotlinx.coroutines.launch

@Composable
fun ColumnScope.UiUtilsTextField(text: String, label: String, padding: Int, color: Color = MaterialTheme.colors.primary, action: (String) -> Unit) {

    TextField(
        value = text,
        onValueChange = { action(it) },
        label = {
            Text(
                text = label,
                color = MaterialTheme.colors.primary,
            )
        },
        shape = RoundedCornerShape(30f),
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(top = padding.dp, start = 16.dp, end = 16.dp)
            .border(
                border = BorderStroke(3.dp, MaterialTheme.colors.primary),
                shape = RoundedCornerShape(
                    corner = CornerSize(30f),
                )
            ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = color,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.onPrimary,
        ),
    )
}

@Composable
fun ColumnScope.UiUtilsCloseButton(navController: NavController, destination: String) {
    FloatingActionButton(
        onClick = {
            navController.navigate(destination)
        },
        modifier = Modifier
            .width(84.dp)
            .height(84.dp)
            .align(Alignment.End)
            .padding(top = 16.dp, end = 16.dp),
        backgroundColor = Color.Transparent,
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
    ) {
        Icon(
            modifier = Modifier
                .width(34.dp)
                .height(34.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_close),
            contentDescription = "Close",
        )
    }
}

@Composable
fun ColumnScope.UiUtilsBackButton(navController: NavController, destination: String) {
    FloatingActionButton(
        onClick = {
            navController.navigate(destination)
        },
        modifier = Modifier
            .width(84.dp)
            .height(84.dp)
            .align(Alignment.Start)
            .padding(top = 16.dp, end = 16.dp),
        backgroundColor = Color.Transparent,
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
    ) {
        Icon(
            modifier = Modifier
                .width(34.dp)
                .height(34.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_back),
            contentDescription = "Close",
        )
    }
}

@Composable
fun ColumnScope.UiUtilsExtendedFloatingButton(text: String, showProgress: Boolean = false, onClick: () -> Unit) {

    ExtendedFloatingActionButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(10.dp)
            .padding(top = 15.dp)
            .wrapContentWidth()
            .align(Alignment.CenterHorizontally),
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
        backgroundColor = MaterialTheme.colors.primary,
        text = {
            if (showProgress) {
                CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
            }
            Text(
                text = text,
                fontSize = 18.sp,
                color = if (!showProgress) MaterialTheme.colors.onPrimary else Color.Transparent,
                modifier = Modifier
                    .weight(5f)
                    .wrapContentWidth(),
            )

        }
    )
}

@Composable
fun UiUtilsNextButton(navController: NavController, destination: String, padding: Int = 50, text: String = "Дальше") {
    ExtendedFloatingActionButton(
        onClick = { navController.navigate(destination) },
        modifier = Modifier
            .padding(10.dp)
            .padding(top = padding.dp)
            .fillMaxWidth(),

        backgroundColor = MaterialTheme.colors.primary,
        text = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = text,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier.weight(5f),
                )
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_right),
                    modifier = Modifier
                        .width(25.dp)
                        .height(25.dp)
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    contentDescription = "App theme",
                    alignment = Alignment.CenterEnd,
                )
            }
        }
    )
}