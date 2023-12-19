package com.narcis.tddCocktailGame.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreUnitTest {
    @Test
    fun `when incrementing score should increment current score`() {
        val score = Score()

        score.increment()

        assertEquals(
            "Current score should have been 1",
            1,
            score.current,
        )
    }

    @Test
    fun `when incrementing score above  highScore should also increment highScore`() {
        val score = Score()

        score.increment()
        assertEquals(1, score.highest)
    }

    @Test
    fun `when increment score below highScore should not increment highScore`(){
        val score = Score(10)
        score.increment()
        assertEquals(10, score.highest)
    }
}
