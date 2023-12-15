package com.narcis.tddCocktailGame.model

class Question(
    val correctOption: String,
    val incorrectOption: String,
) {
    var answeredOption: String? = null
        private set

    fun answer(option: String): Boolean {
        answeredOption = option

        return correctOption == answeredOption
    }
}
