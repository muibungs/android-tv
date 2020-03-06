package com.example.myapplication.ui

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.bbtvnewmedia.androidtv.ui.SearchActivity

abstract class LeanbackActivity : FragmentActivity() {
    override fun onSearchRequested(): Boolean {
        startActivity(Intent(this, SearchActivity::class.java))
        return true
    }
}