package com.example.nomoneytrade.ui.utils

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nomoneytrade.R

@Composable
fun ColumnScope.UiUtilsPasswordTextField(text: String, label: String, padding: Int, color: Color = MaterialTheme.colors.primary, action: (String) -> Unit) {
    var passwordVisible by remember { mutableStateOf(false) }

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
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = color,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.onPrimary,
        ),
        trailingIcon = {
            val image = if (passwordVisible)
                ImageVector.vectorResource(R.drawable.ic_visibility)
            else ImageVector.vectorResource(R.drawable.ic_visibility_off)

            val description = if (passwordVisible) stringResource(R.string.hide_password) else stringResource(R.string.show_password)

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description, modifier = Modifier.size(24.dp))
            }
        },
    )
}

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
fun ColumnScope.UiUtilsToolbarButton(navController: NavController, destination: String, icon: Int, alignment: Alignment.Horizontal = Alignment.End, onClick: () -> Unit ={}) {
    FloatingActionButton(
        onClick = {
            onClick()
            navController.navigate(destination)
        },
        modifier = Modifier
            .width(84.dp)
            .height(84.dp)
            .align(alignment)
            .padding(top = 16.dp, end = 16.dp),
        backgroundColor = Color.Transparent,
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
    ) {
        Icon(
            modifier = Modifier
                .width(34.dp)
                .height(34.dp),
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = "Close",
        )
    }
}

@Composable
fun ColumnScope.UiUtilsExtendedFloatingButton(text: String, showProgress: Boolean = false, padding: Int = 15, onClick: () -> Unit) {

    ExtendedFloatingActionButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(10.dp)
            .padding(top = padding.dp)
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

@Composable
fun ColumnScope.UiUtilsNoPhotoPlaceholder(
    size: Int,
    cameraSize: Int,
    paddingTop: Int,
    bitmap: Bitmap? = null,
    state: Boolean = true,
    onClick: () -> Unit,
) {
    Box(modifier = Modifier
        .size(size.dp)
        .clip(CircleShape)
        .align(Alignment.CenterHorizontally)) {
        if (state) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_circle_backg),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .clickable {
                        onClick()
                    },
                contentDescription = "profile photo stub",
                contentScale = ContentScale.Fit,
            )
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_camera),
                modifier = Modifier
                    .width(cameraSize.dp)
                    .height(cameraSize.dp)
                    .align(Alignment.Center)
                    .padding(start = 5.dp),
                contentDescription = "profile photo sub camera",
                contentScale = ContentScale.Crop,
            )
        } else {
            bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    modifier = Modifier
                        .size(size.dp)
                        .align(Alignment.Center)
                        .padding(top = 10.dp, start = 5.dp)
                        .clip(CircleShape)
                        .clickable {
                            onClick()
                        },
                    contentDescription = "profile photo",
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Composable
fun UiUtilsLoadingFullScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(color = MaterialTheme.colors.primary, modifier = Modifier.align(Alignment.Center))
    }
}
