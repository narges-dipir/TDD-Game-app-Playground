package com.narcis.tddCocktailGame.common.repository

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.narcis.tddCocktailGame.common.network.CocktailsApi
import com.narcis.tddCocktailGame.common.repository.CocktailsRepository
import com.narcis.tddCocktailGame.common.repository.CocktailsRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RepositoryUnitTest {
    private lateinit var repository: CocktailsRepository
    private lateinit var api: CocktailsApi
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @Before
    fun setUp() {
        api = mock()
        sharedPreferences = mock()
        sharedPreferencesEditor = mock()
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        repository = CocktailsRepositoryImpl(api, sharedPreferences)
    }

    @Test
    fun `save score should save to sharedPreferences`() {
        val score = 100
        repository.saveHighScore(score)

        inOrder(sharedPreferencesEditor) {
            verify(sharedPreferencesEditor).putInt(any(), eq(score))
            verify(sharedPreferencesEditor).apply()
        }
    }

    @Test
    fun `get score should get from shared preferences`() {
        repository.getHighScore()
        verify(sharedPreferences).getInt(any(), any())
    }

    @Test
    fun `save score should not save to sharedPreferences if lower`(){
        val previouslySavedHighScore = 100
        val newHighScore = 10
        val spyRepository = spy(repository)
        doReturn(previouslySavedHighScore)
            .whenever(spyRepository)
            .getHighScore()

        spyRepository.saveHighScore(newHighScore)
        verify(sharedPreferencesEditor, never())
            .putInt(any(), eq(newHighScore))
    }
}
