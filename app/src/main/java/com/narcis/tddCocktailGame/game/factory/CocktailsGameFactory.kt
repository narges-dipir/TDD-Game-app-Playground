package com.narcis.tddCocktailGame.game.factory

import com.narcis.tddCocktailGame.game.model.Game
import javax.security.auth.callback.Callback

interface CocktailsGameFactory {
    fun buildGame(callback: Callback)
    interface Callback {
        fun onSuccess(game: Game)
        fun onError()
    }
}
