package com.example.nomoneytrade.productView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.nomoneytrade.R
import com.example.nomoneytrade.showcase.ProductPreview

@Composable
fun ProductInfoScreen(product: ProductPreview, viewModel: ProductInfoViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 12.dp)
        .verticalScroll(rememberScrollState())) {

        val userState = viewModel.sellerInfo.collectAsState()

        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = product.imageUrl)
                    .allowHardware(false)
                    .build()
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.CenterHorizontally),
            contentDescription = "App Icon",
            contentScale = ContentScale.Crop,
        )

        Text(
            text = product.title,
            fontSize = 28.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Text(
            text = product.description,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 12.dp),
            textAlign = TextAlign.Start,
        )

        Row(modifier = Modifier.height(60.dp)) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = userState.value.iconUrl)
                        .allowHardware(false)
                        .build()
                ),
                modifier = Modifier
                    .size(60.dp)
                    .padding(top = 12.dp)
                    .clip(CircleShape),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            Text(
                text = "${userState.value.username} (${userState.value.fio})",
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterVertically)
                    .padding(start = 8.dp)
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start,
            )
        }

        Text(
            text = userState.value.city,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            textAlign = TextAlign.Start,
        )
        Text(
            text = userState.value.address,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            textAlign = TextAlign.Start,
        )
    }
}