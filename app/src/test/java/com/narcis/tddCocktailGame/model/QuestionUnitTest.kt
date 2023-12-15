package com.narcis.tddCocktailGame.model

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class QuestionUnitTest {

    @Test
    fun `when creating question should not have answered option`() {
            val question = Question("CORRECT", "")
        assertNull(question.answeredOption)
    }
}