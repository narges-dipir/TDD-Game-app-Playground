package com.example.wishlist.persistance

import com.example.wishlist.model.Wishlist
import java.util.UUID
import java.util.concurrent.ThreadLocalRandom

object WishlistFactory {
    private fun makeRandomString() = UUID.randomUUID().toString()

    private fun makeRandomInt() = ThreadLocalRandom.current().nextInt(0, 1000 + 1)

    fun makeWishList(): Wishlist {
        return Wishlist(
            makeRandomString(),
            listOf(makeRandomString(), makeRandomString()),
            makeRandomInt(),
        )
    }
}