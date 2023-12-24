package com.narcis.tddCocktailGame.game.factory

import com.narcis.tddCocktailGame.common.repository.CocktailsRepository
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CocktailsGameFactoryUnitTests {
    private lateinit var repository: CocktailsRepository
    private lateinit var factory: CocktailsGameFactory

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
}
