package com.example.myapplication.ui.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.loader.app.LoaderManager
import com.example.myapplication.model.CardListRow
import com.example.myapplication.model.CardRow
import com.bbtvnewmedia.androidtv.ui.SearchActivity
import com.example.myapplication.ui.presenter.CardPresenterSelector
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.google.gson.Gson
import timber.log.Timber


class MainFragment : BrowseSupportFragment(){

    private var mRowsAdapter: ArrayObjectAdapter? = null
    private var mGridItemListRow: ListRow? = null
    var mVideoListRowArray: ArrayList<ListRow>? = null

    private val BACKGROUND_UPDATE_DELAY = 300
    private val mHandler = Handler()
    private var mCategoryRowAdapter: ArrayObjectAdapter? = null
    private var mDefaultBackground: Drawable? = null
    private var mMetrics: DisplayMetrics? = null
    private var mBackgroundTask: Runnable? = null
    private val mBackgroundURI: Uri? = null
    private var mBackgroundManager: BackgroundManager? = null
    private val mLoaderManager: LoaderManager? = null
    private val CATEGORY_LOADER = 123

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        prepareBackgroundManager()
        setupUIElements()
        setupRowAdapter()
//        setOnclickListener()
        prepareEntranceTransition()

//        mCategoryRowAdapter = ArrayObjectAdapter(ListRowPresenter())
//        adapter = mCategoryRowAdapter
    }

    private fun setupUIElements() {
//        badgeDrawable = activity!!.resources.getDrawable(R.drawable.ic_settings_apps, null)
//        title = getString(R.string.app_name) // Badge, when set, takes precedent over title
//        headersState = HEADERS_ENABLED
//        isHeadersTransitionOnBackEnabled = true
        // Set fastLane (or headers) background color
        brandColor = ContextCompat.getColor(activity!!, R.color.background_gradient_start)
        // Set search icon color.
//        searchAffordanceColor = ContextCompat.getColor(activity!!, R.color.search_opaque)
//        setHeaderPresenterSelector(object : PresenterSelector() {
//            override fun getPresenter(o: Any): Presenter {
//                return IconHeaderItemPresenter()
//            }
//        })
    }

    private fun setupRowAdapter() {
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = mRowsAdapter
        Handler().postDelayed({
            loadRows()
            startEntranceTransition()
        }, 500)
    }

    private fun loadRows() {
        /* GridItemPresenter */
//        val gridItemPresenterHeader = IconHeaderItem(0, "GridItemPresenter", R.drawable.ic_guidedstep_option_b)
//        val mGridPresenter = GridItemPresenter()
//        val gridRowAdapter = ArrayObjectAdapter(mGridPresenter)
//        gridRowAdapter.add("1")
//        gridRowAdapter.add("2")
//        gridRowAdapter.add("3")
//        gridRowAdapter.add("4")
//        mGridItemListRow = ListRow(gridItemPresenterHeader, gridRowAdapter)

        val json: String? = Utils.inputStreamToString(resources.openRawResource(R.raw.cards_example))
        val rows: Array<CardRow> = Gson().fromJson<Array<CardRow>>(json, Array<CardRow>::class.java)
        Timber.d("jsonData : $json")
        Timber.d("gsonConverted : $rows")
        for (row in rows) {
            mRowsAdapter?.add(createCardRow(row))
        }
    }

    private fun createCardRow(cardRow: CardRow): Row? {
        return when (cardRow.type) {
            CardRow.TYPE_SECTION_HEADER -> SectionRow(HeaderItem(cardRow.title))
            CardRow.TYPE_DIVIDER -> DividerRow()
            CardRow.TYPE_DEFAULT -> {
                // Build main row using the ImageCardViewPresenter.
                val presenterSelector: CardPresenterSelector? = activity?.let { CardPresenterSelector(it) }
                val listRowAdapter = ArrayObjectAdapter(presenterSelector)
                for (card in cardRow.cards!!) {
                    listRowAdapter.add(card)
                }
                CardListRow(HeaderItem(cardRow.title), listRowAdapter, cardRow)
            }
            else -> {
                val presenterSelector: CardPresenterSelector? = activity?.let { CardPresenterSelector(it) }
                val listRowAdapter = ArrayObjectAdapter(presenterSelector)
                for (card in cardRow.cards!!) {
                    listRowAdapter.add(card)
                }
                CardListRow(HeaderItem(cardRow.title), listRowAdapter, cardRow)
            }
        }
    }

    private fun setRows() {
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        if (mVideoListRowArray != null) {
            for (videoListRow in mVideoListRowArray!!) {
                mRowsAdapter?.add(videoListRow)
            }
        }
        if (mGridItemListRow != null) {
            mRowsAdapter?.add(mGridItemListRow)
        }
        adapter = mRowsAdapter
    }

    private fun setOnclickListener(){
        setOnSearchClickedListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager?.attach(activity!!.window)
        mDefaultBackground = resources.getDrawable(R.drawable.default_background, null)
        mBackgroundTask = UpdateBackgroundTask()
        mMetrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(mMetrics)
    }

    inner class UpdateBackgroundTask : Runnable {
        override fun run() {
            if (mBackgroundURI != null) {
                updateBackground(mBackgroundURI.toString())
            }
        }
    }

    private fun updateBackground(uri: String) {
        val width = mMetrics!!.widthPixels
        val height = mMetrics!!.heightPixels
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .error(mDefaultBackground)
        Glide.with(this)
            .asBitmap()
            .load(uri)
            .apply(options)
            .into(object : SimpleTarget<Bitmap?>(width, height) {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                ) {
                    mBackgroundManager!!.setBitmap(resource)
                }
            })
    }
}