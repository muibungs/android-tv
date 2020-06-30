package com.example.myapplication.model

import com.amazonaws.amplify.generated.graphql.ContentsQuery
import com.amazonaws.amplify.generated.graphql.LiveProgrammeQuery


data class HomeCategory(
    val title: String,
    val style: String,
    val category: String,
    var items: List<Category>
)

data class HomeContent(
    val title: String,
    val category: String,
    var items: List<ContentsQuery.Content>
)

data class HomeLiveSport(
    val title: String,
    val category: String,
    var items: List<LiveProgrammeQuery.LiveProgramme>
)

class AdItem