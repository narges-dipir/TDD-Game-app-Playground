package com.narcis.tddCocktailGame.model

import org.junit.Assert.assertEquals
import org.junit.Test

class GameUnitTests {

    @Test
    fun `when incrementing score should increment current score`() {
        val game = Game()
        game.incrementScore()
        assertEquals(1, game.currentScore)
    }

    @Test
    fun `when incrementing score above highScore should also increment highScore`() {
        val game = Game()
        game.incrementScore()
        assertEquals(1, game.highScore)
    }

}
