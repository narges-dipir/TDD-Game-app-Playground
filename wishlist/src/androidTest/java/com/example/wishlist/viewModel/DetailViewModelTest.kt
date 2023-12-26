package com.example.wishlist.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.wishlist.dataPersistance.RepositoryImpl
import com.example.wishlist.dataPersistance.WishlistDao
import com.example.wishlist.dataPersistance.WishlistDaoImpl
import com.example.wishlist.model.Wishlist
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val wishlistDao: WishlistDao = Mockito.spy(WishlistDaoImpl())
    private val viewModel = DetailViewModel(RepositoryImpl(wishlistDao))

    @Test
    fun saveNewItemCallsDatabase() {
        val wishItem = Wishlist("Victoria", listOf("RW Android Apprentice book", "AndroidPhone"), 1)
        viewModel.saveNewItem(wishItem, "smart watch")
        verify(wishlistDao).save(any())
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun saveNewItemSavesData() = runTest {
        val wishItem = Wishlist("Victoria", listOf("RW Android Apprentice book", "AndroidPhone"), 1)
        val name = "smart watch"
        viewModel.saveNewItem(wishItem, name)
        val mockObserver = mock<Observer<Wishlist>>()
        runBlocking(Dispatchers.Main) {
            wishlistDao.findById(wishItem.id)
                .observeForever(mockObserver)
            verify(mockObserver).onChanged(wishItem.copy(wishes = wishItem.wishes + name))
        }
    }
}