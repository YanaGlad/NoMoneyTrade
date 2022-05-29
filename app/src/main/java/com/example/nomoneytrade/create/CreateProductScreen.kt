package com.example.nomoneytrade.create

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nomoneytrade.CURRENT_USER_ID
import com.example.nomoneytrade.MAIN_SCREEN
import com.example.nomoneytrade.ONBOARDING_SCREEN
import com.example.nomoneytrade.R
import com.example.nomoneytrade.SIGN_IN_SCREEN
import com.example.nomoneytrade.api.requests.ProductRequest
import com.example.nomoneytrade.mvi.event.CreateProductEvent
import com.example.nomoneytrade.ui.utils.UiUtilsExtendedFloatingButton
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
fun CreateProductScreen(navController: NavController, viewModel: CreateProductViewModel) {
    val interactionResult: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val f: File = File(navController.context.cacheDir, "test.jpeg")
            f.createNewFile()

            val bitmap = MediaStore.Images.Media.getBitmap(
                navController.context.contentResolver,
                it.data?.data
            )
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos)
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
        }
    }

    BackHandler {
        navController.navigate(MAIN_SCREEN)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 50.dp)
        .verticalScroll(rememberScrollState())) {

        val eventState = viewModel.event.collectAsState()
        val title = stringResource(R.string.done)
        val description = stringResource(R.string.success_created)
        var progressState by remember { mutableStateOf(false) }
        var userSigned by remember { mutableStateOf(CURRENT_USER_ID != -1L) }

        when (eventState.value) {
            CreateProductEvent.Error -> {
                progressState = false
                //TODO тост с ошибкой
            }
            CreateProductEvent.Loading -> {}
            CreateProductEvent.Success -> {
                progressState = false
                navController.navigate("$ONBOARDING_SCREEN/$title/$description")
                viewModel.event.value = null
            }
        }

        UiUtilsToolbarButton(navController, MAIN_SCREEN, R.drawable.ic_close)
        if (userSigned) {
            CreateNewProduct(viewModel, interactionResult, progressState)
        } else {
            NeedToSignIn(navController)
        }
    }
}

@Composable
private fun ColumnScope.NeedToSignIn(navController: NavController) {
    Text(
        text = stringResource(R.string.need_auth),
        fontSize = 28.sp,
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(top = 8.dp),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )

    Image(
        imageVector = ImageVector.vectorResource(R.drawable.ic_exit),
        modifier = Modifier
            .width(280.dp)
            .height(280.dp)
            .align(Alignment.CenterHorizontally)
            .padding(top = 40.dp, start = 5.dp)
            .clickable {
                navController.navigate("$SIGN_IN_SCREEN/${true}")
            },
        contentDescription = "sign in icon",
        contentScale = ContentScale.Fit,
    )
    Text(
        text = stringResource(R.string.sign_in),
        fontSize = 22.sp,
        modifier = Modifier
            .wrapContentWidth()
            .align(Alignment.CenterHorizontally)
            .clickable {
                navController.navigate(SIGN_IN_SCREEN)
            }
            .padding(top = 8.dp),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )

}

@Composable
private fun ColumnScope.CreateNewProduct(
    viewModel: CreateProductViewModel,
    interactionResult: ActivityResultLauncher<Intent>,
    progressState: Boolean,
) {
    var progressState1 = progressState
    Text(
        text = stringResource(R.string.new_listing),
        fontSize = 28.sp,
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(top = 8.dp),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )

    var titleText by remember { mutableStateOf("") }
    UiUtilsTextField(label = stringResource(R.string.title), padding = 15, text = titleText, color = MaterialTheme.colors.primary) { text ->
        titleText = text
    }

    var descriptionText by remember { mutableStateOf("") }
    UiUtilsTextField(label = stringResource(R.string.description), padding = 15, text = descriptionText, color = MaterialTheme.colors.primary) { text ->
        descriptionText = text
    }

    var tagsText by remember { mutableStateOf("") }
    UiUtilsTextField(label = stringResource(R.string.tags), padding = 15, text = tagsText, color = MaterialTheme.colors.primary) { text ->
        tagsText = text
    }

    var exchangeText by remember { mutableStateOf("") }
    UiUtilsTextField(label = stringResource(R.string.what_for_exchange), padding = 15, text = exchangeText, color = MaterialTheme.colors.primary) { text ->
        exchangeText = text
    }

    var cityText by remember { mutableStateOf("") }
    UiUtilsTextField(label = stringResource(R.string.meeting_place), padding = 15, text = cityText, color = MaterialTheme.colors.primary) { text ->
        cityText = text
    }

    var timeText by remember { mutableStateOf("") }
    UiUtilsTextField(label = stringResource(R.string.meeting_time), padding = 15, text = timeText, color = MaterialTheme.colors.primary) { text ->
        timeText = text
    }

    Text(
        text = stringResource(R.string.upload_image),
        fontSize = 22.sp,
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(top = 8.dp),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )

    Image(
        imageVector = ImageVector.vectorResource(R.drawable.ic_top_from_arrow),
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .align(Alignment.CenterHorizontally)
            .padding(top = 10.dp, start = 5.dp)
            .clip(CircleShape)
            .clickable {
                viewModel.chooseImage(interactionResult)
            },
        contentDescription = "upload icon",
        contentScale = ContentScale.Crop,
    )


    UiUtilsExtendedFloatingButton(
        text = stringResource(R.string.create),
        showProgress = progressState1,
        padding = 0
    ) {
        progressState1 = true
        viewModel.clickCreate(
            ProductRequest(
                title = titleText,
                user_id = CURRENT_USER_ID,
                tags = tagsText.split(" #"),
                description = descriptionText,
                tagsExchange = exchangeText.split(" #"),
                city = cityText,
                time = timeText,
            )
        )
    }
}