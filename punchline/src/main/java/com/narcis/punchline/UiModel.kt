package com.narcis.punchline

sealed class UiModel {

    data class Success(val joke: Joke) : UiModel()

    data class Error(val error: String) : UiModel()
}
