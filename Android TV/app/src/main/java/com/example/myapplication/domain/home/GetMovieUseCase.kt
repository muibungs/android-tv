package com.example.myapplication.domain.home

import com.amazonaws.amplify.generated.graphql.ContentsQuery
import com.amazonaws.amplify.generated.graphql.GetDramaQuery
import com.amazonaws.amplify.generated.graphql.GetHomeQuery
import com.amazonaws.amplify.generated.graphql.GetMovieQuery
import com.example.myapplication.data.HomeRepository
import com.example.myapplication.data.result.Result
import com.example.myapplication.domain.MediatorUseCase
import com.example.myapplication.model.Category
import com.example.myapplication.model.EntryItem
import com.example.myapplication.model.HomeCategory
import timber.log.Timber

class GetMovieUseCase(private val repository: HomeRepository) :
    MediatorUseCase<Boolean, List<GetMovieQuery.Content>>() {
    override fun execute(parameters: Boolean) {
        val items = arrayListOf<GetMovieQuery.Content>()

        repository.getMovie(GetMovieQuery.builder().build(), parameters, {
            if (it.hasErrors()) {
                result.postValue(Result.Error(Exception(it.errors().first().message())))
                it.errors().forEach { error ->
                    Timber.e("${error.message()}")
                }
                return@getMovie
            }
            items.clear()
            it.data()?.Contents().let { list ->
                list?.forEach { content ->
                    items.add(
                        GetMovieQuery.Content(
                            content.__typename(),
                            content.entry_id(),
                            content.channel_id(),
                            content.channel_name(),
                            content.title(),
                            content.description(),
                            content.description_url(),
                            content.thumb(),
                            content.thumb_vertical(),
                            content.thumb_horizontal(),
                            content.content_url(),
                            content.entry_date(),
                            content.views(),
                            content.path()
                        )
                    )
                }
            }
            result.postValue(Result.Success(items))
        }, {
            result.postValue(Result.Error(it))
        })
    }
}