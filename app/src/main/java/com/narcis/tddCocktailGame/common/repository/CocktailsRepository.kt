package com.narcis.tddCocktailGame.common.repository

interface CocktailsRepository {
    fun saveHighScore(score: Int)
}