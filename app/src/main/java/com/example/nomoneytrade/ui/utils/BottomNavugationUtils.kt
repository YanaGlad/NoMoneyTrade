package com.example.nomoneytrade.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.nomoneytrade.R
import com.example.nomoneytrade.mvi.event.BottomNavEvent
import com.example.nomoneytrade.ui.navigation.NavigationItem

@Composable
fun BottomNavigationBar(onClick: (BottomNavEvent) -> Unit) {
    val items = listOf(
        NavigationItem.Offers(stringResource(R.string.offers)),
        NavigationItem.Showcase(stringResource(R.string.listings)),
        NavigationItem.Profile(stringResource(R.string.profile))
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                modifier = Modifier
                    .height(100.dp)
                    .padding(top = 20.dp),
                icon = {
                    Image(
                        modifier = Modifier
                            .height(44.dp)
                            .width(28.dp)
                            .padding(top = 8.dp),
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item._title,
                        contentScale = ContentScale.FillBounds,
                    )
                },
                label = { Text(text = item._title) },
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    onClick(item.event)
                }
            )
        }
    }
}