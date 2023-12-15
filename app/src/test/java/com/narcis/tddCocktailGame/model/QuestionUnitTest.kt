package com.narcis.tddCocktailGame.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class QuestionUnitTest {

    @Test
    fun `when creating question should not have answered option`() {
        val question = Question("CORRECT", "INCORRECT")
        assertNull(question.answeredOption)
    }

    @Test
    fun `when answering should have answered option`() {
        val question = Question("CORRECT", "INCORRECT")
        question.answer("INCORRECT")
        assertEquals("INCORRECT", question.answeredOption)
    }
}
