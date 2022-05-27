package com.example.nomoneytrade.offers

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.nomoneytrade.R
import com.example.nomoneytrade.entity.Offer
import com.example.nomoneytrade.mvi.event.OfferEvent

@Composable
fun OffersScreen(navController: NavController, viewModel: OffersViewModel) {
    val eventState = viewModel.event.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        when (val event = eventState.value) {
            OfferEvent.Error -> {}
            is OfferEvent.LoadedOffers -> {
                event.offers.forEach {
                    OfferItem(it)
                }
            }
            OfferEvent.Loading -> {}
        }
    }
}

@Composable
fun OfferItem(offer: Offer) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier.padding(top = 16.dp)) {
            ShortInfo(itemIcon = offer.userListing.imageUrl, userIcon = offer.user.iconUrl, title = offer.userListing.title)
            ShortInfo(itemIcon = offer.theirListing.imageUrl, userIcon = offer.seller.iconUrl, title = offer.theirListing.title)
        }

        Row {
            OfferCard(1f, Color.Green, R.drawable.ic_checkmark) {
                //POST accepted offer
            }
            OfferCard(1f, Color.Red, R.drawable.ic_close){
                // POST declined offer
            }
        }
    }
}

@Composable
fun RowScope.ShortInfo(itemIcon: String, userIcon: String, title: String) {
    Column(modifier = Modifier.weight(1f)) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = itemIcon)
                        .allowHardware(false)
                        .build()
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp),
                contentDescription = "1st item",
                contentScale = ContentScale.Crop,
            )
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = userIcon)
                        .allowHardware(false)
                        .build()
                ),
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
                    .clip(CircleShape),
                contentDescription = "1st item",
                contentScale = ContentScale.Crop,
            )
        }
        Text(
            text = title,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun RowScope.OfferCard(weight: Float, color: Color, icon: Int, onClick: ()-> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = color,
        modifier = Modifier
            .weight(weight)
            .wrapContentHeight()
            .padding(top = 12.dp, end = 8.dp, start = 8.dp)
            .clickable(
                onClick = {
                    onClick()
                }
            )) {
        Image(
            imageVector = ImageVector.vectorResource(id = icon),
            modifier = Modifier
                .size(45.dp)
                .padding(top = 10.dp, start = 5.dp, bottom = 10.dp),
            contentDescription = "profile photo",
            contentScale = ContentScale.Fit,
        )
    }
}