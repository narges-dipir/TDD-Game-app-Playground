package com.narcis.punchline

import io.reactivex.Single

interface Repository {
    fun getJoke(): Single<Joke>
}

class RepositoryImpl(private val service: JokeService) : Repository {

    override fun getJoke(): Single<Joke> {
        return Single.error(NotImplementedError())
    }
}
