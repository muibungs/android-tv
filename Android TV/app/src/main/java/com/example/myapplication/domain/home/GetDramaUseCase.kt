package com.example.myapplication.domain.home

import com.amazonaws.amplify.generated.graphql.GetDramaQuery
import com.amazonaws.amplify.generated.graphql.GetHomeQuery
import com.example.myapplication.data.HomeRepository
import com.example.myapplication.data.result.Result
import com.example.myapplication.domain.MediatorUseCase
import com.example.myapplication.model.Category
import com.example.myapplication.model.HomeCategory
import timber.log.Timber

class GetDramaUseCase(private val repository: HomeRepository) :
    MediatorUseCase<Boolean, List<Category>>() {
    override fun execute(parameters: Boolean) {
        val items = arrayListOf<Category>()

        repository.getDrama(GetDramaQuery.builder().build(), parameters, {
            if (it.hasErrors()) {
                result.postValue(Result.Error(Exception(it.errors().first().message())))
                it.errors().forEach { error ->
                    Timber.e("${error.message()}")
                }
                return@getDrama
            }
            items.clear()
            it.data()?.Categories().let { list ->
                list?.forEach { item ->
                    items.add(
                        Category(
                            _typename = item.__typename(),
                            parentId = item.parent_id() ?: "",
                            category_id = item.category_id(),
                            category_name = item.category_name() ?: "",
                            category_description = item.category_description() ?: "",
                            category_image = item.category_image() ?: "",
                            category_image_cover = item.category_image_cover() ?: "",
                            category_sort = item.category_sort() ?: "",
                            category_video_cat = item.category_video_cat() ?: "",
                            category_video_group = item.category_video_group() ?: "",
                            path = item.path() ?: "",
                            content_bugaboointer_url = null
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