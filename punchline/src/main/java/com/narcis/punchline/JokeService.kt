package com.narcis.punchline

import io.reactivex.Single
import retrofit2.http.GET

interface JokeService {
    @GET("joke.json")
    fun getRandomJoke(): Single<Joke>
}