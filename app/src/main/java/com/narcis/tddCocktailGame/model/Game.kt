package com.narcis.tddCocktailGame.model

class Game(
    highest: Int = 0,
    private val questions: List<Question>,
) {
    var currentScore = 0
        private set

    var highScore = highest
        private set

    private var questionIndex = -1
    fun incrementScore() {
        currentScore++
        if (currentScore > highScore) {
            highScore++
        }
    }

    fun nextQuestion(): Question? {
        if (questionIndex + 1 < questions.size) {
            questionIndex++

            return questions[questionIndex]
        }
        return null
    }

    fun answer(question: Question, option: String) {
        question.answer(option)
    }
}
