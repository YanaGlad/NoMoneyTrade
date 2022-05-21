package com.example.nomoneytrade.showcase

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nomoneytrade.R
import com.example.nomoneytrade.mvi.event.ShowcaseEvent
import com.example.nomoneytrade.ui.utils.ComposeScreen
import kotlinx.coroutines.flow.collect

class ShowcaseScreen(private val navController: NavController, val viewModel: ShowcaseViewModel) : ComposeScreen<ShowcaseViewModel>(navController, viewModel) {

    override val ON_CLOSE_DESTINATION: String? = null
    override val ON_BACK_DESTINATION: String? = null
    override val showCloseButton: Boolean = false
    override val showBackButton: Boolean = false

    @Composable
    override fun Screen() {

    }

    @Composable
    override fun ObserveViewModel() {

        LaunchedEffect(viewModel) {
            viewModel.event.collect { event ->
                when(event) {
                    ShowcaseEvent.Error -> {}
                    ShowcaseEvent.Loading -> {}
                    is ShowcaseEvent.Success -> {

                    }
                }
            }
        }
    }
}

@Composable
private fun ProductList(list: List<ProductPreview>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        list.forEach {
            ProductListItem(productPreview = it)
        }
    }
}

@Composable
private fun ProductListItem(productPreview: ProductPreview) {
    //TODO загрузка по url
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.stub),
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .align(CenterVertically)
                .padding(top = 10.dp, start = 5.dp),
            contentDescription = "profile photo",
            contentScale = ContentScale.Crop,
        )
    }
}
