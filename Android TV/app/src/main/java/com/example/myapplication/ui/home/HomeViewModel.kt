package com.example.myapplication.ui.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amazonaws.amplify.generated.graphql.GetMovieQuery
import com.example.myapplication.data.result.Result
import com.example.myapplication.domain.home.GetDramaUseCase
import com.example.myapplication.domain.home.GetHomesUseCase
import com.example.myapplication.domain.home.GetMovieUseCase
import com.example.myapplication.model.Category

class HomeViewModel(
    private val getHomesUseCase: GetHomesUseCase,
    private val getDramaUseCase: GetDramaUseCase,
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel(){
    val homes: MediatorLiveData<Result<List<Any>>>
    val dramaRerun: MediatorLiveData<Result<List<Category>>>
    val movies: MediatorLiveData<Result<List<GetMovieQuery.Content>>>
    init {
        homes = getHomesUseCase.observe()
        dramaRerun = getDramaUseCase.observe()
        movies = getMovieUseCase.observe()
    }

    fun getHome(isForceFetch: Boolean) {
        getHomesUseCase.execute(isForceFetch)
    }

    fun getDramaRerun(isForceFetch: Boolean){
        getDramaUseCase.execute(isForceFetch)
    }

    fun getMovies(isForceFetch: Boolean){
        getMovieUseCase.execute(isForceFetch)
    }
}