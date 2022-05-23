package com.example.nomoneytrade.offers.entity

data class Offer(
    val userId: Int,
    val theirId: Int,
    val userListingId: Int,
    val theirListingId: Int,
    val place: String,
    val time: String,
) {

    //  card with (your listing for their offer)
    // avatar and name of the seller int the top left corner bold
    // In the bottom you see city and suggested date+time
    // when you click on the card (no buttons!, it expands and shows you decline and checkmark icons

    //!!! need fun to get listing by it's id
    //
}