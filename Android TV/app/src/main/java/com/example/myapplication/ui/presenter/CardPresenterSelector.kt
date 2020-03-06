package com.example.myapplication.ui.presenter

import android.content.Context
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector
import com.example.myapplication.model.Card
import java.util.*

class CardPresenterSelector(private val mContext: Context) :
    PresenterSelector() {
    private val presenters: HashMap<Card.Type?, Presenter?> =
        HashMap<Card.Type?, Presenter?>()

    override fun getPresenter(item: Any): Presenter {
        if (item !is Card) throw RuntimeException(
            String.format(
                "The PresenterSelector only supports data items of type '%s'",
                Card::class.java.getName()
            )
        )
        val card: Card = item
        var presenter = ImageCardViewPresenter(mContext)

        presenters[card.type] = presenter

        return presenter
    }

}
