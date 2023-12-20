package com.narcis.tddCocktailGame.game.model

class Game(
    val score: Score = Score(0),
    private val questions: List<Question>,
) {
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
            score.increment()
        }
    }
}
