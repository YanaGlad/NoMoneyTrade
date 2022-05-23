package com.example.nomoneytrade.ui.navigation

import com.example.nomoneytrade.R
import com.example.nomoneytrade.mvi.event.BottomNavEvent

sealed class NavigationItem(val _title: String, val event: BottomNavEvent, val icon: Int) {

    class Profile(val title: String) : NavigationItem(_title = title, event = BottomNavEvent.Profile, icon = R.drawable.ic_profile)
    class Showcase(val title: String) : NavigationItem(_title = title, event = BottomNavEvent.Showcase, icon = R.drawable.ic_listing)
    class Offers(val title: String) : NavigationItem(_title = title, event = BottomNavEvent.Offers, icon = R.drawable.ic_message_v2)
}


