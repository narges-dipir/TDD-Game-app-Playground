package com.narcis.tddCocktailGame.model

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException

const val INCORRECT = "INCORRECT"

const val CORRECT = "CORRECT"

const val INVALID = "INVALID"

class QuestionUnitTest {
    private lateinit var question: Question

    @Before
    fun setUp() {
        question = Question(CORRECT, INCORRECT)
    }

    @Test
    fun `when creating question should not have answered option`() {
        assertNull(question.answeredOption)
    }

    @Test
    fun `when answering should have answered option`() {
        question.answer(INCORRECT)
        assertEquals(INCORRECT, question.answeredOption)
    }

    @Test
    fun `when answering with correct option should return true`() {
        val result = question.answer(CORRECT)
        assertTrue(result)
    }

    @Test
    fun `when answering with incorrect option should Return false`() {
        val result = question.answer(INCORRECT)
        assertFalse(result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `when answering with invalid option should throw exception`() {
        question.answer(INVALID)
    }

    @Test
    fun `when creating question should return option with custom sort`() {
        val options = question.getOptions { it.reversed() }

        Assert.assertEquals(listOf(INCORRECT, CORRECT), options)
    }
}
