package com.narcis.punchline.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.narcis.punchline.Joke
import com.narcis.punchline.JokeService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeServiceTestUsingWebServer {
    private val testJson = """{"id":1, "joke": "joke"}"""
    @get:Rule
    val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val jokeService by lazy {
        retrofit.create(JokeService::class.java)
    }

    @Test
    fun getRandomJokeEmitsJoke() {
        mockWebServer.enqueue(
            MockResponse().setBody(testJson).setResponseCode(200)
        )
        val testObserver = jokeService.getRandomJoke().test()
        testObserver.assertValue(Joke("1", "joke"))
    }
}