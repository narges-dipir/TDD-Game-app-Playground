package com.narcis.tddCocktailGame.game.factory

import com.narcis.tddCocktailGame.common.network.Cocktail
import com.narcis.tddCocktailGame.common.repository.CocktailsRepository
import com.narcis.tddCocktailGame.common.repository.RepositoryCallback

class CocktailsGameFactoryImpl(private val repository: CocktailsRepository) :
    CocktailsGameFactory {
    override fun buildGame(callback: CocktailsGameFactory.Callback) {
        repository.getAlcoholic(
            object : RepositoryCallback<List<Cocktail>, String> {
                override fun onSuccess(t: List<Cocktail>) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: String) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
}
