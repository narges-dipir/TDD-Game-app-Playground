package com.narcis.tddCocktailGame.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class GameUnitTests {

    @Test
    fun `when incrementing score should increment current score`() {
        val game = Game(0)
        game.incrementScore()
        assertEquals(1, game.currentScore)
    }

    @Test
    fun `when incrementing score above highScore should also increment highScore`() {
        val game = Game(0)
        game.incrementScore()
        assertEquals(1, game.highScore)
    }

    @Test
    fun `when incrementing score below highScore should not increment high score`() {
        val game = Game(10)
        game.incrementScore()
        assertEquals(10, game.highScore)
    }

    @Test
    fun `when ask next question return not null question`() {
        val game = Game(10, 5)
        val question: Question = game.nextQuestion()
        assertNotNull(question)
    }
}
