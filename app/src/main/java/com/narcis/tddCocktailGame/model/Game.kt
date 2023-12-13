package com.narcis.tddCocktailGame.model

class Game(highest: Int) {
    var currentScore = 0
        private set

    var highScore = highest
        private set

    fun incrementScore() {
        currentScore++
        highScore++
    }
}
