package com.narcis.tddCocktailGame.game.model

import com.narcis.tddCocktailGame.game.model.Game
import com.narcis.tddCocktailGame.game.model.Question
import com.narcis.tddCocktailGame.game.model.Score
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.*

const val OPTION = "OPTION"

class GameUnitTests {
    @Test
    fun `when getting next question should return question`() {
        val question_one = Question(CORRECT, INCORRECT)
        val questions = listOf(question_one)
        val game = Game(questions = questions)
        val nextQuestion = game.nextQuestion()
        assertSame(question_one, nextQuestion)
    }

    @Test
    fun `when getting next question without more questions should return null`() {
        val question_one = Question(CORRECT, INCORRECT)
        val questions = listOf(question_one)
        val game = Game(questions = questions)

        game.nextQuestion()
        val nextQuestion = game.nextQuestion()

        assertNull(nextQuestion)
    }

    @Test
    fun `when answering should delegate to question`() {
        val question = mock<Question>()
        val game = Game(questions = listOf(question))
        game.answer(question, OPTION)
        verify(question, times(1)).answer(eq(OPTION))
    }

    @Test
    fun `when answering correctly should increment current score`() {
        val question = mock<Question>()
        whenever(question.answer(anyString())).thenReturn(true)
        val score = mock<Score>()
        val game = Game(score, questions = listOf(question))
        game.answer(question, OPTION)
        verify(score).increment()
    }

    @Test
    fun `when answering incorrectly should not increment current score`() {
        val question = mock<Question>()
        whenever(question.answer(anyString())).thenReturn(false)
        val score = mock<Score>()
        val game = Game(score, questions = listOf(question))
        verify(score, never()).increment()
    }
}
