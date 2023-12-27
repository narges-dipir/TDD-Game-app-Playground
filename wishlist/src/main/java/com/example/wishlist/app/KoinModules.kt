package com.example.wishlist.app

import androidx.room.Room
import com.example.wishlist.dataPersistance.Repository
import com.example.wishlist.dataPersistance.RepositoryImpl
import com.example.wishlist.dataPersistance.WishlistDatabase
import com.example.wishlist.viewModel.DetailViewModel
import com.example.wishlist.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Repository> { RepositoryImpl(get()) }

    single {
        Room.databaseBuilder(
            get(),
            WishlistDatabase::class.java,
            "wishlist-database",
        )
            .allowMainThreadQueries()
            .build()
            .wishListDao()
    }

    viewModel { MainViewModel(get()) }

    viewModel { DetailViewModel(get()) }
}
