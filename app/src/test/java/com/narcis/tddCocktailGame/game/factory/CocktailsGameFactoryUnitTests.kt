package com.narcis.tddCocktailGame.game.factory

import com.narcis.tddCocktailGame.common.network.Cocktail
import com.narcis.tddCocktailGame.common.repository.CocktailsRepository
import com.narcis.tddCocktailGame.common.repository.RepositoryCallback
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CocktailsGameFactoryUnitTests {
    private lateinit var repository: CocktailsRepository
    private lateinit var factory: CocktailsGameFactory

    private val cocktails = listOf(
        Cocktail("1", "Drink1", "image1"),
        Cocktail("2", "Drink2", "image2"),
        Cocktail("3", "Drink3", "image3"),
        Cocktail("4", "Drink4", "image4"),
    )

    @Before
    fun setUp() {
        repository = mock()
        factory = CocktailsGameFactoryImpl(repository)
    }

    @Test
    fun `build game should get cocktails from repo`() {
        factory.buildGame(mock())
        verify(repository).getAlcoholic(any())
    }

    @Test
    fun `buildGame should call onSuccess`() {
        val callback = mock<CocktailsGameFactory.Callback>()
        setUpRepositoryWithCocktails(repository)
        factory.buildGame(callback)
        verify(callback).onSuccess(any())
    }

    @Test
    fun `buildGame should call onError`() {
        val callback = mock<CocktailsGameFactory.Callback>()
        setUpRepositoryWithError(repository)
        factory.buildGame(callback)
        verify(callback).onError()
    }
    private fun setUpRepositoryWithCocktails(
        repository: CocktailsRepository,
    ) {
        doAnswer {
            val callback: RepositoryCallback<List<Cocktail>, String> = it.getArgument(0)
            callback.onSuccess(cocktails)
        }.whenever(repository).getAlcoholic(any())
    }
    private fun setUpRepositoryWithError(
        repository: CocktailsRepository,
    ) {
        doAnswer {
            val callback: RepositoryCallback<List<Cocktail>, String> = it.getArgument(0)
            callback.onError("Error")
        }.whenever(repository).getAlcoholic(any())
    }
}
