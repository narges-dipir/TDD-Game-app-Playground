package com.narcis.punchline

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.javafaker.Faker
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

@get:Rule
val mockProvider = MockProviderRule.create { clazz ->
    Mockito.mock(clazz.java)
}
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private val faker = Faker()

    @Test
    fun onLaunchButtonIsDisplayed() {
        
    }
}