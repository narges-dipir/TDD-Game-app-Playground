package com.narcis.punchline.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.narcis.punchline.Joke
import com.narcis.punchline.JokeService
import com.narcis.punchline.RepositoryImpl
import io.reactivex.Single
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertEquals

private const val id = "1"
private const val joke = "how does a train eat? it goes chew, chew"
class JokeServiceTestUsingWebServer {
    private val testJson = """{"id":$id, "joke": "$joke"}"""
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
        testObserver.assertValue(Joke(id, joke))
    }

    @Test
    fun `get random joke gets random joke json`(){
        mockWebServer.enqueue(
            MockResponse().setBody(testJson).setResponseCode(200)
        )

        val testObserver = jokeService.getRandomJoke().test()
        testObserver.assertNoErrors()
        assertEquals("/random_joke.json", mockWebServer.takeRequest().path)
    }
}
class JokeServiceTestMockingService {
    private val jokeService: JokeService = mock()
    private val repository = RepositoryImpl(jokeService)

    @Test
    fun getRandomJokeEmitsJoke() {
        val joke = Joke(id, joke)

        whenever(jokeService.getRandomJoke()).thenReturn(Single.just(joke))
        val testObserver = repository.getJoke().test()
        testObserver.assertValue(joke)
    }
}