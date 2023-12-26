package com.example.wishlist.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wishlist.dataPersistance.Repository
import com.example.wishlist.model.Wishlist

class DetailViewModel(private val repository: Repository) : ViewModel() {

    fun saveNewItem(wishlist: Wishlist, name: String) {
        repository.saveWishlistItem(wishlist.copy(wishes = wishlist.wishes + name))
    }

    fun getWishlist(id: Int): LiveData<Wishlist> {
        return repository.getWishlist(0)
    }
}