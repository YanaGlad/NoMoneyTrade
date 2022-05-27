package com.example.nomoneytrade.create

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.nomoneytrade.R
import com.example.nomoneytrade.api.dto.ProductDto
import com.example.nomoneytrade.api.requests.ProductRequest
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.CreateProductEvent
import com.example.nomoneytrade.ui.utils.UiUtilsExtendedFloatingButton
import com.example.nomoneytrade.ui.utils.UiUtilsTextField
import com.example.nomoneytrade.ui.utils.UiUtilsToolbarButton
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.*

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

    Column(modifier = Modifier.fillMaxSize()) {

        val eventState = viewModel.event.collectAsState()
        when(eventState.value){
            CreateProductEvent.Error -> {}
            CreateProductEvent.Loading -> {}
            CreateProductEvent.Success -> {
                //TODO сделать экран "Объявлеие создано!"
            }
        }

        UiUtilsToolbarButton(navController, MAIN_SCREEN, R.drawable.ic_close)
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

        // TODO згрузка картинок!!!

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

        Text(
            text = stringResource(R.string.upload_image),
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    viewModel.chooseImage(interactionResult)
                }
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
                .padding(top = 10.dp, start = 5.dp),
            contentDescription = "upload icon",
            contentScale = ContentScale.Crop,
        )

        UiUtilsExtendedFloatingButton(
            text = "Создать",
            showProgress = false //state.loading
        ) {
            viewModel.clickCreate(
                ProductRequest(
                    title = titleText,
                    user_id = CURRENT_USER_ID,
                    tags = tagsText.split(" #"),
                    description = descriptionText,
                    tagsExchange = tagsText.split(" #"),
                    // TODO add tagsExchange
                )
            )
        }
    }
}