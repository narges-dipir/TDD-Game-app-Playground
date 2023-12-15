package com.narcis.tddCocktailGame.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

private const val INCORRECT = "INCORRECT"

private const val CORRECT = "CORRECT"

class QuestionUnitTest {

    @Test
    fun `when creating question should not have answered option`() {
        val question = Question(CORRECT, INCORRECT)
        assertNull(question.answeredOption)
    }

    @Test
    fun `when answering should have answered option`() {
        val question = Question(CORRECT, INCORRECT)
        question.answer(INCORRECT)
        assertEquals(INCORRECT, question.answeredOption)
    }

    @Test
    fun `when answering with correct option should return true`() {
        val question = Question(CORRECT, INCORRECT)
        val result = question.answer(CORRECT)
        assertTrue(result)
    }

    @Test
    fun `when answering with incorrect option should Return false`() {
        val question = Question(CORRECT, INCORRECT)
        val result = question.answer(INCORRECT)
        assertFalse(result)
    }
}
