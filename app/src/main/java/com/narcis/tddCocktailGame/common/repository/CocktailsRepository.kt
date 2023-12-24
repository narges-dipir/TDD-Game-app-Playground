package com.narcis.tddCocktailGame.common.repository

import com.narcis.tddCocktailGame.common.network.Cocktail

interface CocktailsRepository {
    fun getAlcoholic(callback: RepositoryCallback<List<Cocktail>, String>)
    fun saveHighScore(score: Int)
    fun getHighScore(): Int
}
interface RepositoryCallback<T, E> {
    fun onSuccess(t: T)
    fun onError(e: E)
}
