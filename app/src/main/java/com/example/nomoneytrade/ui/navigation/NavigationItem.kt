package com.example.nomoneytrade.ui.navigation

import com.example.nomoneytrade.PROFILE_SCREEN
import com.example.nomoneytrade.R
import com.example.nomoneytrade.SHOWCASE_SCREEN
import com.example.nomoneytrade.SUGGESTIONS_SCREEN

sealed class NavigationItem(val _title: String, val destination: String, val icon: Int) {

    class Profile(val title: String) : NavigationItem(_title = title, destination = PROFILE_SCREEN, icon = R.drawable.ic_profile)
    class Showcase(val title: String) : NavigationItem(_title = title, destination = SHOWCASE_SCREEN, icon = R.drawable.ic_listing)
    class Suggestions(val title: String) : NavigationItem(_title = title, destination = SUGGESTIONS_SCREEN, icon = R.drawable.ic_message_v2)
}


