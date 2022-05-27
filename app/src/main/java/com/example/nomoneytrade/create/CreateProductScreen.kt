package com.example.nomoneytrade.create

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.nomoneytrade.CURRENT_USER_ID
import com.example.nomoneytrade.MAIN_SCREEN
import com.example.nomoneytrade.R
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.CreateProductEvent
import com.example.nomoneytrade.ui.utils.UiUtilsExtendedFloatingButton
import com.example.nomoneytrade.ui.utils.UiUtilsTextField
import com.example.nomoneytrade.ui.utils.UiUtilsToolbarButton

@Composable
fun CreateProductScreen(navController: NavController, viewModel: CreateProductViewModel) {
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
                .padding(top = 8.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        UiUtilsExtendedFloatingButton(
            text = "Создать",
            showProgress = false //state.loading
        ) {
            viewModel.clickCreate(
                Product(
                    id = 12, //????  TODO NEED HELP
                    title = titleText,
                    userId = CURRENT_USER_ID,
                    imageUrl = "",
                    favourites = false,
                    tags = tagsText.split(" #"),
                    description = descriptionText,
                )
            )
        }
    }
}