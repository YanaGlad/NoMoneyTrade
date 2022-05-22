package com.example.nomoneytrade.showcase

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nomoneytrade.R
import com.example.nomoneytrade.mvi.event.ShowcaseEvent
import com.example.nomoneytrade.ui.utils.ComposeScreen
import com.example.nomoneytrade.ui.utils.SearchView
import java.util.*

class ShowcaseScreen(navController: NavController, private val viewModel: ShowcaseViewModel) : ComposeScreen<ShowcaseViewModel>(navController, viewModel) {

    override val ON_CLOSE_DESTINATION: String? = null
    override val ON_BACK_DESTINATION: String? = null
    override val showCloseButton: Boolean = false
    override val showBackButton: Boolean = false

    @Composable
    override fun Screen() {

        val viewState = viewModel.event.collectAsState()

        when (val event = viewState.value) {
            ShowcaseEvent.Error -> {}
            ShowcaseEvent.Loading -> {}
            is ShowcaseEvent.Success -> {
                ProductList(event.products)
            }
        }
    }
}

@Composable
private fun ProductList(list: List<ProductPreview>) {

    Column {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        SearchView(textState)

        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
        ) {

            list.filter {
                it.title.lowercase(Locale.getDefault()).contains(textState.value.text.lowercase(Locale.getDefault()))
            }
                .forEach {
                    ProductListItem(productPreview = it)
                }
        }
    }
}

@Composable
private fun ProductListItem(productPreview: ProductPreview) {
    //TODO загрузка по url
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.padding(top = 16.dp, end = 8.dp, start = 8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)) {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.stub),
                modifier = Modifier
                    .width(140.dp)
                    .align(CenterVertically)
                    .padding(top = 10.dp, start = 5.dp, bottom = 10.dp),
                contentDescription = "profile photo",
                contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier.padding(start = 6.dp, top = 5.dp)) {
                Text(
                    text = productPreview.title,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = productPreview.tags.getTags(),
                    fontSize = 10.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                )

                Text(
                    text = productPreview.description.getModifiedText(),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp),
                )
            }
        }
    }
}

private fun buildAnnotatedStringWithColors(text: String): AnnotatedString {
    val words: List<String> = text.split("\\s+".toRegex())
    val colors = listOf(Color.Red, Color.Magenta, Color.Blue, Color.Green).shuffled() //create custom colors with theme!

    val builder = AnnotatedString.Builder()
    for ((count, word) in words.withIndex()) {
        builder.withStyle(style = SpanStyle(color = colors[count % 4])) {
            append("$word ")
        }
    }
    return builder.toAnnotatedString()
}

private fun List<String>.getTags(): String {
    var result = ""
    this.forEach {
        result += "$it "
    }
    return result
}

private fun String.getModifiedText(): String {
    return if (length > 75)
        substring(0, 70) + "..."
    else
        this
}
