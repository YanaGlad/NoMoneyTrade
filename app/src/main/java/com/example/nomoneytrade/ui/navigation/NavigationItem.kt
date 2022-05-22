package com.example.nomoneytrade.ui.navigation

sealed class NavigationItem {

    class Profile(): NavigationItem()
    class Showcase(): NavigationItem()
    class Suggestions(): NavigationItem()
}


