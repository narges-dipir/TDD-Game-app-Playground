package com.narcis.tddCocktailGame.game.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.narcis.tddCocktailGame.common.repository.CocktailsRepository
import com.narcis.tddCocktailGame.game.factory.CocktailsGameFactory
import com.narcis.tddCocktailGame.game.model.Game
import com.narcis.tddCocktailGame.game.model.Question
import com.narcis.tddCocktailGame.game.model.Score
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CocktailsGameViewModelUnitTests {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: CocktailsRepository
    private lateinit var factory: CocktailsGameFactory
    private lateinit var viewModel: CocktailsGameViewModel
    private lateinit var game: Game
    private lateinit var loadingObserver: Observer<Boolean>
    private lateinit var errorObserver: Observer<Boolean>
    private lateinit var scoreObserver: Observer<Score>
    private lateinit var questionObserver: Observer<Question>

    @Before
    fun setUp() {
        repository = mock()
        factory = mock()
        viewModel = CocktailsGameViewModel(repository, factory)

        game = mock()

        loadingObserver = mock()
        errorObserver = mock()
        questionObserver = mock()
        scoreObserver = mock()
        viewModel.getLoading().observeForever(loadingObserver)
        viewModel.getScore().observeForever(scoreObserver)
        viewModel.getQuestion().observeForever(questionObserver)
        viewModel.getError().observeForever(errorObserver)
    }

    private fun setUpFactoryWithSuccessGame(game: Game) {
        doAnswer {
            val callback: CocktailsGameFactory.Callback = it.getArgument(0)
            callback.onSuccess(game)
        }.whenever(factory).buildGame(any())
    }

    @Test
    fun `init should buildGame`() {
        viewModel.initGame()
        verify(factory).buildGame(any())
    }

    @Test
    fun `init should hide error`() {
        viewModel.initGame()

        verify(errorObserver).onChanged(eq(false))
    }

    @Test
    fun `init should show error when factory returns error`() {
        setUpFactoryWithError()
        viewModel.initGame()
        verify(errorObserver).onChanged(eq(true))
    }

    @Test
    fun `init should hide loading when factory returns error`() {
        setUpFactoryWithError()
        viewModel.initGame()
        verify(loadingObserver).onChanged(eq(false))
    }

    @Test
    fun `init should show score when factory returns success`(){
        val score = mock<Score>()
        whenever(game.score).thenReturn(score)
        setUpFactoryWithSuccessGame(game)

        viewModel.initGame()
        verify(scoreObserver).onChanged(eq(score))
    }

    @Test
    fun `init should show first question when factory returns success`() {
        val question = mock<Question>()
        whenever(game.nextQuestion()).thenReturn(question)
        setUpFactoryWithSuccessGame(game)
        viewModel.initGame()
        verify(questionObserver).onChanged(eq(question))
    }
    private fun setUpFactoryWithError() {
        doAnswer {
            val callback: CocktailsGameFactory.Callback =
                it.getArgument(0)
            callback.onError()
        }.whenever(factory).buildGame(any())
    }
}
