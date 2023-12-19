package com.narcis.tddCocktailGame.model

class Game(
    highest: Int = 0,
    private val questions: List<Question>,
) {
    private val score = Score(highest)
    val currentScore
        get() = score.current

    val highScore
        get() = score.highest

    private var questionIndex = -1
    fun incrementScore() {
        score.increment()
    }

    fun nextQuestion(): Question? {
        if (questionIndex + 1 < questions.size) {
            questionIndex++

            return questions[questionIndex]
        }
        return null
    }

    fun answer(question: Question, option: String) {
        val result = question.answer(option)
        if (result) {
            incrementScore()
        }
    }
}
