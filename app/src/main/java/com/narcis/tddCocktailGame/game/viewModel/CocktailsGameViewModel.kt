package com.narcis.tddCocktailGame.game.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.narcis.tddCocktailGame.common.repository.CocktailsRepository
import com.narcis.tddCocktailGame.game.factory.CocktailsGameFactory
import com.narcis.tddCocktailGame.game.model.Game
import com.narcis.tddCocktailGame.game.model.Question
import com.narcis.tddCocktailGame.game.model.Score

class CocktailsGameViewModel(
    private val repository: CocktailsRepository,
    private val factory: CocktailsGameFactory,
) : ViewModel() {
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Boolean>()
    private val questionLiveData = MutableLiveData<Question>()
    private val scoreLiveData = MutableLiveData<Score>()
    fun initGame() {
        factory.buildGame(object : CocktailsGameFactory.Callback{
            override fun onSuccess(game: Game) {
                TODO("Not yet implemented")
            }

            override fun onError() {
                TODO("Not yet implemented")
            }

        })
    }
    fun getLoading(): LiveData<Boolean> = loadingLiveData
    fun getError(): LiveData<Boolean> = errorLiveData
    fun getQuestion(): LiveData<Question> = questionLiveData

    fun getScore(): LiveData<Score> = scoreLiveData
}
