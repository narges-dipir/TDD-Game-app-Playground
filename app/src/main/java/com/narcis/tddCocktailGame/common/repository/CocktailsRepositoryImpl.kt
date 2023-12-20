package com.narcis.tddCocktailGame.common.repository

import android.content.SharedPreferences
import com.narcis.tddCocktailGame.common.network.CocktailsApi

class CocktailsRepositoryImpl(
    private val api: CocktailsApi,
    private val sharedPreferences: SharedPreferences,
) : CocktailsRepository {
    override fun saveHighScore(score: Int) {
        TODO("Not yet implemented")
    }
}
