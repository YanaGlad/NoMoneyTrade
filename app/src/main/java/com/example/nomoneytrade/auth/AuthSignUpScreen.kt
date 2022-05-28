package com.example.nomoneytrade.auth

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
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
import com.example.nomoneytrade.SIGN_IN_SCREEN
import com.example.nomoneytrade.mvi.event.AuthEvent
import com.example.nomoneytrade.ui.utils.UiUtilsExtendedFloatingButton
import com.example.nomoneytrade.ui.utils.UiUtilsPasswordTextField
import com.example.nomoneytrade.ui.utils.UiUtilsTextField
import com.example.nomoneytrade.ui.utils.UiUtilsToolbarButton
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun AuthSignUpScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    var bitmapState: Bitmap? by remember { mutableStateOf(null) }

    val interactionResult = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val f = File(navController.context.cacheDir, "test.jpeg")
            f.createNewFile()

            val bitmap = MediaStore.Images.Media.getBitmap(navController.context.contentResolver, it.data?.data)
            val bos = ByteArrayOutputStream()
            bitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, bos)

            viewModel.updatePhoto(bitmap)

            val byteArray: ByteArray = bos.toByteArray()
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(f)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            try {
                fos!!.write(byteArray)
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val reqFile: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), f)
            viewModel.imageFile = MultipartBody.Part.createFormData("file", f.name, reqFile)
            Log.d("Resp", viewModel.imageFile.toString())
        }
    }

    var progressState by remember { mutableStateOf(false) }

    val eventState = viewModel.event.collectAsState()

    when (val event = eventState.value) {
        is AuthEvent.Error -> {
        }
        is AuthEvent.Loading -> {
            progressState = true
        }
        is AuthEvent.Success -> {
            progressState = false
            navController.navigate(MAIN_SCREEN)
        }
        is AuthEvent.FailedToLogin -> {

        }
        is AuthEvent.UpdatedPhoto -> {
            bitmapState = event.bitmap
        }
        AuthEvent.None -> {
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        UiUtilsToolbarButton(navController = navController, destination = SIGN_IN_SCREEN, icon = R.drawable.ic_back, alignment = Alignment.Start)

        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(CenterHorizontally)
                .padding(top = 20.dp)
                .clip(CircleShape)
        ) {
            if (bitmapState == null) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_circle_backg),
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(170.dp)
                        .align(Center)
                        .clickable {
                            viewModel.chooseImage(interactionResult)
                        } ,
                    contentDescription = "profile photo",
                    contentScale = ContentScale.Crop,
                )
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_camera),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .align(Center)
                        .padding(start = 5.dp),
                    contentDescription = "profile photo",
                    contentScale = ContentScale.Crop,
                )
            } else {
                Image(
                    bitmap = bitmapState!!.asImageBitmap(),
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxHeight()
                        .align(Center)
                        .clip(CircleShape)
                        .clickable {
                            viewModel.chooseImage(interactionResult)
                        }
                        .padding(top = 10.dp, start = 5.dp),
                    contentDescription = "profile photo",
                    contentScale = ContentScale.Fit,
                )
            }
        }

        Text(
            text = stringResource(R.string.registration),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
        )


        var username by remember { mutableStateOf("") }
        UiUtilsTextField(label = stringResource(R.string.username), padding = 15, text = username) { text ->
            username = text
        }

        var email by remember { mutableStateOf("") }
        UiUtilsTextField(label = stringResource(R.string.email), padding = 15, text = email) { text ->
            email = text
        }

        var phone by remember { mutableStateOf("") }
        UiUtilsTextField(label = stringResource(R.string.phone_number), padding = 15, text = phone) { text ->
            phone = text
        }

        var password by remember { mutableStateOf("") }
        UiUtilsPasswordTextField(label = stringResource(R.string.password), padding = 15, text = password) { text ->
            password = text
        }

        var confirmPassword by remember { mutableStateOf("") }
        UiUtilsPasswordTextField(label = stringResource(R.string.confirmPassword), padding = 15, text = confirmPassword) { text ->
            confirmPassword = text
        }

        UiUtilsExtendedFloatingButton(stringResource(R.string.sign_up), showProgress = progressState) {
            viewModel.signUpClick(username = username, password = password, email = email, phoneNumber = phone)
        }

        Text(
            text = stringResource(R.string.already_have_account),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = true) {
                   navController.navigate(SIGN_IN_SCREEN)
                },
        )
    }
}
