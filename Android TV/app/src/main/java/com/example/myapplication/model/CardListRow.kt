package com.example.myapplication.model

import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ObjectAdapter

class CardListRow(
    header: HeaderItem? = HeaderItem(""),
    adapter: ObjectAdapter?,
    cardRow: CardRow?
) :
    ListRow(header, adapter) {
    var cardRow: CardRow? = null

    init {
        this.cardRow = cardRow
    }
}