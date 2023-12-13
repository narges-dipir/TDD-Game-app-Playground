package com.narcis.tddCocktailGame.model

class Game {
    var currentScore = 0
        private set

    var highScore = 0
        private set

    fun incrementScore() {
        currentScore++
    }
}
