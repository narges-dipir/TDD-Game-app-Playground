package com.narcis.tddCocktailGame.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class GameUnitTests {

    @Test
    fun `when incrementing score should increment current score`() {
        val game = Game(0, emptyList())
        game.incrementScore()
        assertEquals(1, game.currentScore)
    }

    @Test
    fun `when incrementing score above highScore should also increment highScore`() {
        val game = Game(0, emptyList())
        game.incrementScore()
        assertEquals(1, game.highScore)
    }

    @Test
    fun `when incrementing score below highScore should not increment high score`() {
        val game = Game(10, emptyList())
        game.incrementScore()
        assertEquals(10, game.highScore)
    }

    @Test
    fun `when getting next question should return question`() {
        val question_one = Question(CORRECT, INCORRECT)
        val questions = listOf(question_one)
        val game = Game(questions = questions)
        val nextQuestion = game.nextQuestion()
        assertSame(question_one, nextQuestion)
    }
}
