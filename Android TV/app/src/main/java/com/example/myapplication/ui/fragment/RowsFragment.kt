package com.bbtvnewmedia.androidtv.ui.fragment

import android.os.Bundle
import android.os.Handler
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.example.myapplication.model.CardListRow
import com.example.myapplication.model.CardRow
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.example.myapplication.ui.presenter.CardPresenterSelector
import com.google.gson.Gson
import timber.log.Timber

class RowsFragment : RowsSupportFragment() {

    private var mRowsAdapter: ArrayObjectAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUIElement()
    }

    private fun setupUIElement() {
        setupRowAdapter()
    }

    private fun setupRowAdapter() {
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = mRowsAdapter
        Handler().postDelayed({
            loadRows()
        }, 500)
    }

    private fun loadRows() {
        val json: String? =
            Utils.inputStreamToString(resources.openRawResource(R.raw.cards_example))
        val rows: Array<CardRow> = Gson().fromJson<Array<CardRow>>(json, Array<CardRow>::class.java)
        Timber.d("jsonData : $json")
        Timber.d("gsonConverted : $rows")
        for (row in rows) {
            mRowsAdapter?.add(createCardRow(row))
        }
    }

    private fun createCardRow(cardRow: CardRow): Row? {
//        return when (cardRow.type) {
//            CardRow.TYPE_SECTION_HEADER -> SectionRow(HeaderItem(cardRow.title))
//            CardRow.TYPE_DIVIDER -> DividerRow()
//            CardRow.TYPE_DEFAULT -> {
//                // Build main row using the ImageCardViewPresenter.
//                val presenterSelector: CardPresenterSelector? = activity?.let { CardPresenterSelector(it) }
//                val listRowAdapter = ArrayObjectAdapter(presenterSelector)
//                for (card in cardRow.cards!!) {
//                    listRowAdapter.add(card)
//                }
//                CardListRow(HeaderItem(cardRow.title), listRowAdapter, cardRow)
//            }
//            else -> {
//                val presenterSelector: CardPresenterSelector? = activity?.let { CardPresenterSelector(it) }
//                val listRowAdapter = ArrayObjectAdapter(presenterSelector)
//                for (card in cardRow.cards!!) {
//                    listRowAdapter.add(card)
//                }
//                CardListRow(HeaderItem(cardRow.title), listRowAdapter, cardRow)
//            }
//        }
        return if (!cardRow.cards.isNullOrEmpty()) {
                val presenterSelector: CardPresenterSelector? =
                    activity?.let { CardPresenterSelector(it) }
                val listRowAdapter = ArrayObjectAdapter(presenterSelector)
                for (card in cardRow.cards!!) {
                    listRowAdapter.add(card)
                }
                CardListRow(HeaderItem(cardRow.title), listRowAdapter, cardRow)
            } else {

            val listRowAdapter = ArrayObjectAdapter(ListRowPresenter())
            CardListRow(HeaderItem(cardRow.title),listRowAdapter,cardRow)
        }
    }
}
