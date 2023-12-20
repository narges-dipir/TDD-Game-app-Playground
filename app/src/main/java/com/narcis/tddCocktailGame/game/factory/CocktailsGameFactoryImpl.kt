package com.narcis.tddCocktailGame.game.factory

import com.narcis.tddCocktailGame.common.repository.CocktailsRepository

class CocktailsGameFactoryImpl(private val repository: CocktailsRepository) :
    CocktailsGameFactory {
    override fun buildGame(callback: CocktailsGameFactory.Callback) {
        TODO("Not yet implemented")
    }
}
