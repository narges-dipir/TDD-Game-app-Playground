package com.example.wishlist.dataPersistance

import androidx.lifecycle.LiveData
import com.example.wishlist.model.Wishlist

interface Repository {
    fun saveWishlist(wishlist: Wishlist)
    fun getWishlists(): LiveData<List<Wishlist>>
    fun getWishlist(id: Int): LiveData<Wishlist>
    fun saveWishlistItem(wishlist: Wishlist, name: String)
}