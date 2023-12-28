package com.narcis.punchline

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

  single<Repository> { RepositoryImpl(get()) }

  single<JokeService> {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://rw-punchline.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    retrofit.create<JokeService>(JokeService::class.java)
  }

  viewModel { MainViewModel(get()) }
}

