package com.example.wishlist.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wishlist.dataPersistance.RepositoryImpl
import com.example.wishlist.dataPersistance.WishlistDao
import com.example.wishlist.dataPersistance.WishlistDaoImpl
import com.example.wishlist.model.Wishlist
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

@RunWith(AndroidJUnit4::class)
class DetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val wishlistDao: WishlistDao = Mockito.spy(WishlistDaoImpl())
    private val viewModel = DetailViewModel(RepositoryImpl(wishlistDao))

    @Test
    fun saveNewItemCallsDatabase() {
        val wishItem = Wishlist("Victoria", listOf("RW Android Apprentice book", "AndroidPhone"), 1)
        viewModel.saveNewItem(wishItem, "smart watch")
        verify(wishlistDao).save(any())
    }
}