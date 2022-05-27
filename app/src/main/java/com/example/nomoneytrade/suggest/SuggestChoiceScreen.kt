package com.example.nomoneytrade.suggest

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
import com.example.nomoneytrade.R
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.SuggestChoiceEvent
import internal.ProductListItem

@Composable
fun SuggestChoiceScreen(tags: String, navController: NavController, viewModel: SuggestChoiceViewModel) {
    val tagsList = tags.split(" ")
    val eventState = viewModel.event.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        when (val event = eventState.value) {
            SuggestChoiceEvent.Error -> {}
            SuggestChoiceEvent.Loading -> {}
            is SuggestChoiceEvent.Success -> {
                val filteredItems = event.availableProducts
                    .filter { product ->
                        var check = false
                        for (t in product.tags) {
                            for (exT in tagsList) {
                                check = t == exT
                            }
                        }
                        check
                    }

                if (filteredItems.isNotEmpty()) {
                    event.availableProducts.forEach {
                        ProductListItem(it) {

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