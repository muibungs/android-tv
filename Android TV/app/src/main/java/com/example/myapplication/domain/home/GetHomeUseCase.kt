package com.example.myapplication.domain.home

import com.amazonaws.amplify.generated.graphql.ContentsQuery
import com.amazonaws.amplify.generated.graphql.GetHomeQuery
import com.amazonaws.amplify.generated.graphql.LiveProgrammeQuery
import com.example.myapplication.model.Category
import com.example.myapplication.model.HomeCategory
import com.example.myapplication.model.HomeContent
import com.example.myapplication.model.HomeLiveSport
import com.example.myapplication.data.HomeRepository
import com.example.myapplication.domain.MediatorUseCase
import com.example.myapplication.data.result.Result
import timber.log.Timber

class GetHomesUseCase(
    private val repository: HomeRepository
) : MediatorUseCase<Boolean, List<Any>>() {

    override fun execute(parameters: Boolean) {
        val items = arrayListOf<Any>()

        repository.getHome(GetHomeQuery.builder().build(), parameters, {
            if (it.hasErrors()) {
                result.postValue(Result.Error(Exception(it.errors().first().message())))
                it.errors().forEach { error ->
                    Timber.e("${error.message()}")
                }
                return@getHome
            }

            items.clear()

            it.data()?.Billboard()?.let { list ->
                items.add(
                    HomeContent(
                        title = "บอร์ด",
                        category = "billboard",
                        items = list.map { content ->
                            ContentsQuery.Content(
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
                                null,
                                content.path()
                            )
                        })
                )
            }

            it.data()?.BugabooInter()?.let { list ->
                items.add(
                    HomeCategory(
                        title = "Premium Subscription",
                        style = "poster",
                        category = "premium_subscription",
                        items = list.map { item ->
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
                                content_bugaboointer_url = item.content_bugaboointer_url() ?: ""
                            )
                        })
                )
            }

            it.data()?.DramaOnAir()?.let { list ->
                items.add(
                    HomeCategory(
                        title = "ละครที่กำลังออกอากาศ",
                        style = "poster",
                        category = "drama_onair",
                        items = list.map { item ->
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
                        })
                )
            }

            it.data()?.FinEx()?.let { list ->
                if (!list.isNullOrEmpty()) {
                    items.add(
                        HomeCategory(
                            title = "FINFIN CHANNEL",
                            style = "poster",
                            category = "finfin_channel",
                            items = list.map { item ->
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
                            })
                    )
                }
            }

            it.data()?.OriginalContent()?.let { list ->
                items.add(
                    HomeCategory(
                        title = "ORIGINAL CONTENT",
                        style = "poster_land",
                        category = "original_content",
                        items = list.map { item ->
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
                        })
                )
            }

            it.data()?.Bugaboo()?.let { list ->
                items.add(
                    HomeCategory(
                        title = "Ch7HD ORIGINAL CONTENT",
                        style = "poster_land",
                        category = "ch7hd_original_content",
                        items = list.map { item ->
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
                        })
                )
            }

            it.data()?.DramaRerun()?.let { list ->
                items.add(
                    HomeCategory(
                        title = "ละครย้อนหลัง",
                        style = "poster",
                        category = "drama",
                        items = list.map { item ->
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
                        })
                )
            }

            it.data()?.Recommended()?.let { list ->
                items.add(
                    HomeContent(
                        title = "แนะนำ",
                        category = "suggestion",
                        items = list.map { content ->
                            ContentsQuery.Content(
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
                                null,
                                content.path()
                            )
                        })
                )
            }

            it.data()?.Podcast()?.let { list ->
                items.add(
                    HomeContent(
                        "BUGABOO PODCAST",
                        "podcast",
                        list.map { content ->
                            ContentsQuery.Content(
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
                                null,
                                content.path()
                            )
                        })
                )
            }

            it.data()?.News()?.let { list ->
                items.add(
                    HomeContent(
                        title = "ข่าว",
                        category = "news",
                        items = list.map { content ->
                            ContentsQuery.Content(
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
                                null,
                                content.path()
                            )
                        })
                )
            }

            it.data()?.Entertain()?.let { list ->
                items.add(
                    HomeContent(
                        title = "ข่าวบันเทิง",
                        category = "entertainment_news",
                        items = list.map { content ->
                            ContentsQuery.Content(
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
                                null,
                                content.path()
                            )
                        })
                )
            }

            it.data()?.Sports()?.let { list ->
                items.add(
                    HomeContent(
                        title = "ข่าวกีฬา",
                        category = "sport_news",
                        items = list.map { content ->
                            ContentsQuery.Content(
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
                                null,
                                content.path()
                            )
                        })
                )
            }

            it.data()?.Muaythai()?.let { list ->
                items.add(
                    HomeContent(
                        "มวยไทย 7 สี",
                        "muaythai",
                        list.map { content ->
                            ContentsQuery.Content(
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
                                null,
                                content.path()
                            )
                        })
                )
            }

            it.data()?.Movies()?.let { list ->
                items.add(
                    HomeContent(
                        title = "ดูหนังออนไลน์",
                        category = "movies",
                        items = list.map { content ->
                            ContentsQuery.Content(
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
                                null,
                                content.path()
                            )
                        })
                )
            }

            it.data()?.TvShows()?.let { list ->
                items.add(
                    HomeCategory(
                        title = "รายการ",
                        style = "poster_land",
                        category = "entertain",
                        items = list.map { item ->
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
                        })
                )
            }

            it.data()?.Documentary()?.let { list ->
                items.add(
                    HomeCategory(
                        title = "สารคดี",
                        style = "poster",
                        category = "documentary",
                        items = list.map { item ->
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
                        })
                )
            }

            it.data()?.LiveProgram()?.let { list ->
                items.add(
                    HomeLiveSport(
                        title = "รายการถ่ายทอดสด",
                        category = "program_online",
                        items = list.map { program ->
                            LiveProgrammeQuery.LiveProgramme(
                                program.__typename(),
                                program.path(),
                                program.entry_id(),
                                program.channel_id(),
                                program.channel_name(),
                                program.title(),
                                program.description(),
                                program.thumb(),
                                program.content_url(),
                                program.entry_date(),
                                null
                            )
                        })
                )
            }

            result.postValue(Result.Success(items))
        }, {
            result.postValue(Result.Error(it))
        })
    }
}