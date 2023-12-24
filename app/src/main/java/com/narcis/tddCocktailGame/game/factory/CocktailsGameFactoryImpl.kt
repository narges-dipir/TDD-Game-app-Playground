package com.narcis.tddCocktailGame.game.factory

import com.narcis.tddCocktailGame.common.network.Cocktail
import com.narcis.tddCocktailGame.common.repository.CocktailsRepository
import com.narcis.tddCocktailGame.common.repository.RepositoryCallback
import com.narcis.tddCocktailGame.game.model.Game

class CocktailsGameFactoryImpl(private val repository: CocktailsRepository) :
    CocktailsGameFactory {
    override fun buildGame(callback: CocktailsGameFactory.Callback) {
        repository.getAlcoholic(
            object : RepositoryCallback<List<Cocktail>, String> {
                override fun onSuccess(t: List<Cocktail>) {
                    callback.onSuccess(Game(questions = emptyList()))
                }

                override fun onError(e: String) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
}
