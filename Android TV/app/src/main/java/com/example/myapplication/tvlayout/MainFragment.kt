/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.myapplication.tvlayout

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.example.myapplication.model.CardListRow
import com.example.myapplication.model.CardRow
import com.example.myapplication.ui.presenter.CardPresenterSelector
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.google.gson.Gson
import timber.log.Timber
import java.util.*

/**
 * Loads a grid of cards with movies to browse.
 */
class MainFragment : RowsSupportFragment() {

    private val mHandler = Handler()
    private lateinit var mBackgroundManager: BackgroundManager
    private var mDefaultBackground: Drawable? = null
    private lateinit var mMetrics: DisplayMetrics
    private var mBackgroundTimer: Timer? = null
    private var mBackgroundUri: String? = null

    private var mAdapter: ArrayObjectAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val gridPresenter =
//            VerticalGridPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM)
//        gridPresenter.numberOfColumns = 4
////        setGridPresenter(gridPresenter)
//
//        val mGridPresenter = GridItemPresenter()
//        val gridRowAdapter = ArrayObjectAdapter(mGridPresenter)
//        gridRowAdapter.add(resources.getString(R.string.grid_view))
//        gridRowAdapter.add(getString(R.string.error_fragment))
//        gridRowAdapter.add(resources.getString(R.string.personal_settings))
//
//        adapter = gridRowAdapter
    }

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
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        Log.i(TAG, "onCreate")
//        super.onActivityCreated(savedInstanceState)

//        prepareBackgroundManager()
//
//        setupUIElements()
//
//        loadRows()
//
//        setupEventListeners()


    //    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy: " + mBackgroundTimer?.toString())
//        mBackgroundTimer?.cancel()
//    }
//
//    private fun prepareBackgroundManager() {
//
//        mBackgroundManager = BackgroundManager.getInstance(activity!!)
//        mBackgroundManager.attach(activity!!.window)
//        mDefaultBackground = ContextCompat.getDrawable(activity!!, R.drawable.default_background)
//        mMetrics = DisplayMetrics()
//        activity!!.windowManager.defaultDisplay.getMetrics(mMetrics)
//    }
//
//    private fun setupUIElements() {
////        title = getString(R.string.browse_title)
//        // over title
////        headersState = BrowseFragment.HEADERS_ENABLED
////        isHeadersTransitionOnBackEnabled = true
//
//        // set fastLane (or headers) background color
////        brandColor = ContextCompat.getColor(activity!!, R.color.fastlane_background)
//        // set search icon color
////        searchAffordanceColor = ContextCompat.getColor(activity!!, R.color.search_opaque)
//
//    }
//
//    private fun loadRows() {
////        val list = MovieList.list
//
////        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
////        val cardPresenter = CardPresenter()
////
////        for (i in 0 until NUM_ROWS) {
////            if (i != 0) {
////                Collections.shuffle(list)
////            }
////            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
////            for (j in 0 until NUM_COLS) {
////                listRowAdapter.add(list[j % 5])
////            }
////            val header = HeaderItem(i.toLong(), MovieList.MOVIE_CATEGORY[i])
////            rowsAdapter.add(ListRow(header, listRowAdapter))
////        }
////
////        val gridHeader = HeaderItem(NUM_ROWS.toLong(), "PREFERENCES")
////
////        val mGridPresenter = GridItemPresenter()
////        val gridRowAdapter = ArrayObjectAdapter(mGridPresenter)
////        gridRowAdapter.add(resources.getString(R.string.grid_view))
////        gridRowAdapter.add(getString(R.string.error_fragment))
////        gridRowAdapter.add(resources.getString(R.string.personal_settings))
////        rowsAdapter.add(ListRow(gridHeader, gridRowAdapter))
//
////        adapter = rowsAdapter
//    }
//
//    private fun setupEventListeners() {
//        setOnSearchClickedListener {
//            Toast.makeText(activity!!, "Implement your own in-app search", Toast.LENGTH_LONG)
//                .show()
//        }
//
//        onItemViewClickedListener = ItemViewClickedListener()
////        onItemViewSelectedListener = ItemViewSelectedListener()
//    }
//
//    private inner class ItemViewClickedListener : OnItemViewClickedListener {
//        override fun onItemClicked(
//            itemViewHolder: Presenter.ViewHolder,
//            item: Any,
//            rowViewHolder: RowPresenter.ViewHolder,
//            row: Row
//        ) {
//
//            if (item is Movie) {
//                Log.d(TAG, "Item: " + item.toString())
//                val intent = Intent(activity!!, DetailsActivity::class.java)
//                intent.putExtra(DetailsActivity.MOVIE, item)
//
//                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    activity!!,
//                    (itemViewHolder.view as ImageCardView).mainImageView,
//                    DetailsActivity.SHARED_ELEMENT_NAME
//                )
//                    .toBundle()
//                activity!!.startActivity(intent, bundle)
//            } else if (item is String) {
//                if (item.contains(getString(R.string.error_fragment))) {
//                    val intent = Intent(activity!!, BrowseErrorActivity::class.java)
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(activity!!, item, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    private inner class ItemViewSelectedListener : OnItemViewSelectedListener {
//        override fun onItemSelected(
//            itemViewHolder: Presenter.ViewHolder?, item: Any?,
//            rowViewHolder: RowPresenter.ViewHolder, row: Row
//        ) {
//            if (item is Movie) {
//                mBackgroundUri = item.backgroundImageUrl
//                startBackgroundTimer()
//            }
//        }
//    }
//
//    private fun updateBackground(uri: String?) {
//        val width = mMetrics.widthPixels
//        val height = mMetrics.heightPixels
//        Glide.with(activity!!)
//            .load(uri)
//            .centerCrop()
//            .error(mDefaultBackground)
//            .into<SimpleTarget<GlideDrawable>>(
//                object : SimpleTarget<GlideDrawable>(width, height) {
//                    override fun onResourceReady(
//                        resource: GlideDrawable,
//                        glideAnimation: GlideAnimation<in GlideDrawable>
//                    ) {
//                        mBackgroundManager.drawable = resource
//                    }
//                })
//        mBackgroundTimer?.cancel()
//    }
//
//    private fun startBackgroundTimer() {
//        mBackgroundTimer?.cancel()
//        mBackgroundTimer = Timer()
//        mBackgroundTimer?.schedule(UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY.toLong())
//    }
//
//    private inner class UpdateBackgroundTask : TimerTask() {
//
//        override fun run() {
//            mHandler.post { updateBackground(mBackgroundUri) }
//        }
//    }
//
    private inner class GridItemPresenter : Presenter() {
        override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
            val view = TextView(parent.context)
            view.layoutParams = ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT)
            view.isFocusable = true
            view.isFocusableInTouchMode = true
            view.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.default_background))
            view.setTextColor(Color.WHITE)
            view.gravity = Gravity.CENTER
            return Presenter.ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
            (viewHolder.view as TextView).text = item as String
        }

        override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {}
    }

    //
    companion object {
        private val TAG = "MainFragment"

        private val BACKGROUND_UPDATE_DELAY = 300
        private val GRID_ITEM_WIDTH = 200
        private val GRID_ITEM_HEIGHT = 200
        private val NUM_ROWS = 6
        private val NUM_COLS = 15
    }
}
