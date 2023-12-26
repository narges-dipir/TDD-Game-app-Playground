package com.example.wishlist.screen

import androidx.compose.runtime.Composable
import com.example.wishlist.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel(),
) {
}