package com.example.wishlist.persistance

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wishlist.dataPersistance.WishlistDao
import com.example.wishlist.dataPersistance.WishlistDatabase
import com.example.wishlist.model.Wishlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(AndroidJUnit4::class)
class WishlistDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var wishlistDatabase: WishlistDatabase
    private lateinit var wishlistDao: WishlistDao

    @Before
    fun initDb() {
        wishlistDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WishlistDatabase::class.java,
        ).build()

        wishlistDao = wishlistDatabase.wishListDao()
    }

    @Test
    fun getAllReturnsEmptyList() = runTest {
        val testObserver: Observer<List<Wishlist>> = mock()
        runBlocking(Dispatchers.Main) {
            wishlistDao.getAll().observeForever(testObserver)
            delay(1000)
            verify(testObserver).onChanged(emptyList())
        }
    }

    @Test
    fun saveWishlistsSavesData() = runTest {
        val wishItem1 = WishlistFactory.makeWishList()
        val wishItem2 = WishlistFactory.makeWishList()
        wishlistDao.save(wishItem1, wishItem2)

        runBlocking(Dispatchers.Main) {
            val testObserver: Observer<List<Wishlist>> = mock()
            wishlistDao.getAll().observeForever(testObserver)

            val listClass = ArrayList::class.java as Class<ArrayList<Wishlist>>
            val argumentCaptor = ArgumentCaptor.forClass(listClass)
            verify(testObserver).onChanged(argumentCaptor.capture())

            Assert.assertTrue(argumentCaptor.value.size > 0)
        }
    }

    @Test
    fun getAllRetrievesData() {
        val wishItem1 = WishlistFactory.makeWishList()
        val wishItem2 = WishlistFactory.makeWishList()
        wishlistDao.save(wishItem1, wishItem2)
        runBlocking(Dispatchers.Main) {
            val testObserver: Observer<List<Wishlist>> = mock()
            wishlistDao.getAll().observeForever(testObserver)

            val listClass = ArrayList::class.java as Class<ArrayList<Wishlist>>
            val argumentCaptor = ArgumentCaptor.forClass(listClass)

            verify(testObserver).onChanged(argumentCaptor.capture())
            val capturedArgument = argumentCaptor.value

            Assert.assertTrue(capturedArgument.containsAll(listOf(wishItem1, wishItem2)))
        }
    }

    @Test
    fun findByIdRetrievesCorrectData() {
        val wishItem1 = WishlistFactory.makeWishList()
        val wishItem2 = WishlistFactory.makeWishList()
        wishlistDao.save(wishItem1, wishItem2)

        runBlocking(Dispatchers.Main) {
            val testObserver: Observer<Wishlist> = mock()
            wishlistDao.findById(wishItem2.id).observeForever(testObserver)

            verify(testObserver).onChanged(wishItem2)
        }
    }

    @After
    fun closeDb() {
        wishlistDatabase.close()
    }
}