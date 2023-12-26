package com.example.wishlist.app

import com.example.wishlist.dataPersistance.Repository
import com.example.wishlist.dataPersistance.RepositoryImpl
import com.example.wishlist.dataPersistance.WishlistDao
import com.example.wishlist.dataPersistance.WishlistDaoImpl
import com.example.wishlist.viewModel.DetailViewModel
import com.example.wishlist.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Repository> { RepositoryImpl(get()) }

    single<WishlistDao> { WishlistDaoImpl() }

    viewModel { MainViewModel(get()) }

    viewModel { DetailViewModel(get()) }
}
