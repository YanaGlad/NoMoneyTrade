package com.example.nomoneytrade.suggest

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nomoneytrade.ONBOARDING_SCREEN
import com.example.nomoneytrade.R
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.SuggestChoiceEvent
import com.example.nomoneytrade.ui.utils.UiUtilsLoadingFullScreen
import internal.ProductListItem

@Composable
fun SuggestChoiceScreen(tags: String, navController: NavController, viewModel: SuggestChoiceViewModel) {
    val tagsList = tags.replace("+#", " #").split(" #")
    val eventState = viewModel.event.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        val title = stringResource(R.string.done)
        val description = stringResource(R.string.now_meent)
        val drawable = R.drawable.ic_exchange

        when (val event = eventState.value) {
            SuggestChoiceEvent.Error -> {}
            SuggestChoiceEvent.Loading -> UiUtilsLoadingFullScreen()
            is SuggestChoiceEvent.Success -> {
                val filteredItems = event.availableProducts
                    .filter { product ->
                        var check = false
                        for (t in product.tags) {
                            for (exT in tagsList) {
                                if (!check) check = t == exT
                            }
                        }
                        check
                    }

                if (filteredItems.isNotEmpty()) {
                    filteredItems.forEach {
                        ProductListItem(it) {
                            navController.navigate("$ONBOARDING_SCREEN/$title/$description")
                        }
                    }
                } else {
                    Text(
                        text = stringResource(R.string.you_have_no),
                        fontSize = 28.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 8.dp),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}