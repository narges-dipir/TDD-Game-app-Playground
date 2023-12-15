package com.narcis.tddCocktailGame.model

import org.junit.Assert.assertNull
import org.junit.Test

class QuestionUnitTest {

    @Test
    fun `when creating question should not have answered option`() {
        val question = Question("CORRECT", "INCORRECT")
        assertNull(question.answeredOption)
    }
}
