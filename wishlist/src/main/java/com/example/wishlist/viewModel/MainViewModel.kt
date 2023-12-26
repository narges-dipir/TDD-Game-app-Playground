package com.example.wishlist.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wishlist.dataPersistance.Repository
import com.example.wishlist.model.Wishlist

class MainViewModel(private val repository: Repository) : ViewModel() {

    fun saveNewList(name: String) {
        repository.saveWishlist(Wishlist(name, listOf()))
    }

    fun getWishlists(): LiveData<List<Wishlist>> {
        return repository.getWishlists()
    }
}