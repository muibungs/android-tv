package com.bbtvnewmedia.androidtv.ui.fragment

import android.os.Bundle
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.Observer
import com.example.myapplication.model.CardListRow
import com.example.myapplication.model.CardRow
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.example.myapplication.data.result.Result
import com.example.myapplication.ui.home.HomeViewModel
import com.example.myapplication.ui.presenter.*
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RowsFragment : RowsSupportFragment() {

    private var mRowsAdapter: ArrayObjectAdapter? = null
    private val homeViewModel : HomeViewModel by viewModel()
    private lateinit var cardCategoryPresenter : CardCategoryPresenter
    private lateinit var cardContentPresenter: CardContentPresenter
    private var cardDramaRowAdapter = ArrayObjectAdapter()
    private var cardMovieRowAdapter = ArrayObjectAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.view?.setBackgroundResource(R.drawable.background_food)
        setupUIElement()
    }

    private fun setupUIElement() {
        setupRowAdapter()
        setObserve()
    }

    private fun setupRowAdapter() {
        context?.let {
            cardCategoryPresenter = CardCategoryPresenter(it)
            cardDramaRowAdapter = ArrayObjectAdapter(cardCategoryPresenter)
            cardContentPresenter = CardContentPresenter(it)
            cardMovieRowAdapter = ArrayObjectAdapter(cardContentPresenter)
            mRowsAdapter = ArrayObjectAdapter(CustomListRowPresenter())
            adapter = mRowsAdapter
            loadData()
        }
//        Handler().postDelayed({
//            loadDarma()
//        }, 500)
    }

    private fun setObserve(){
        homeViewModel.dramaRerun.observe(this, Observer {
            when(it){
                is Result.Success -> {
                    cardDramaRowAdapter.clear()
                    it.data.forEach { category ->
                        cardDramaRowAdapter.add(category)
                    }
                    val header = IconHeaderItem(0, "ละคร", R.drawable.ic_main_icon)
                    val videoListRow = CustomListRow(header, cardDramaRowAdapter)
                    mRowsAdapter?.add(videoListRow)
                    adapter = mRowsAdapter
                }
            }
        })

        homeViewModel.movies.observe(this, Observer {
            when(it){
                is Result.Success -> {
                    it.data.forEach { content ->
                        cardMovieRowAdapter.add(content)
                    }
                    val header = IconHeaderItem(0, "ดูหนังออนไลน์", R.drawable.ic_main_icon)
                    val videoListRow = CustomListRow(header, cardMovieRowAdapter)
                    mRowsAdapter?.add(videoListRow)
                    adapter = mRowsAdapter
                }
            }
        })
    }

    private fun loadData(){
        homeViewModel.getDramaRerun(true)
        homeViewModel.getMovies(true)
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
