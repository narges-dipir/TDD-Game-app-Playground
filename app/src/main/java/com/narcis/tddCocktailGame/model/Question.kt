package com.narcis.tddCocktailGame.model

class Question(
    val correctOption: String,
    val incorrectOption: String,
) {
    var answeredOption: String? = null
        private set

    fun answer(option: String): Boolean {
        if (option != correctOption && option != incorrectOption) {
            throw IllegalArgumentException("Not a valid option")
        }
        answeredOption = option

        return correctOption == answeredOption
    }
}
