package com.narcis.tddCocktailGame.model.common.repository

import android.content.SharedPreferences
import com.narcis.tddCocktailGame.common.network.CocktailsApi
import com.narcis.tddCocktailGame.common.repository.CocktailsRepositoryImpl
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RepositoryUnitTest {
    @Test
    fun `save score should save to sharedPreferences`() {
        val api: CocktailsApi = mock()
        val sharedPreferencesEditor: SharedPreferences.Editor = mock()
        val sharedPreferences: SharedPreferences = mock()
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)

        val repository = CocktailsRepositoryImpl(api, sharedPreferences)
        val score = 100
        repository.saveHighScore(score)

        inOrder(sharedPreferencesEditor) {
            verify(sharedPreferencesEditor).putInt(any(), eq(score))
            verify(sharedPreferencesEditor).apply()
        }
    }

    @Test
    fun `get score should get from shared preferences`() {
        val api: CocktailsApi = mock()
        val sharedPreferences: SharedPreferences = mock()

        val repository = CocktailsRepositoryImpl(api, sharedPreferences)
        repository.getHighScore()
        verify(sharedPreferences).getInt(any(), any())
    }
}
