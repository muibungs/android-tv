package com.example.myapplication

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.tvlayout.MainFragment
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : FragmentActivity(), View.OnFocusChangeListener {

    var isSelectedMenu: View? = null
    var menuSelection: Boolean = true
    var lastedFocus: View? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            val fragment = MainFragment()
            supportFragmentManager.beginTransaction().replace(R.id.main_browse_fragment, fragment)
                .commit()
        }

        btn1.requestFocus()
        menuSelection = true

        btn1.setOnKeyListener { v, keyCode, event ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                menuCheckSelection(v, keyCode, event)
            }
            false
        }

        btn2.setOnKeyListener { v, keyCode, event ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                menuCheckSelection(v, keyCode, event)
            }
            false
        }

        btn3.setOnKeyListener { v, keyCode, event ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                menuCheckSelection(v, keyCode, event)
            }
            false
        }
        btn4.setOnKeyListener { v, keyCode, event ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                menuCheckSelection(v, keyCode, event)
            }
            false
        }
        mock.setOnKeyListener { v, keyCode, event ->

            mockCheckSelection(v, keyCode, event)
            false
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun mockCheckSelection(v: View, keyCode: Int, event: KeyEvent) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT && event.action == KeyEvent.ACTION_UP) {
            lastedFocus?.requestFocus()
            menuSelection = true
            btn1.background = getDrawable(R.drawable.menu_bg_color_selector)
            btn2.background = getDrawable(R.drawable.menu_bg_color_selector)
            btn3.background = getDrawable(R.drawable.menu_bg_color_selector)
            btn4.background = getDrawable(R.drawable.menu_bg_color_selector)
//            motionLayout.transitionToStart()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun menuCheckSelection(v: View, keyCode: Int, event: KeyEvent) {
        if (!menuSelection) {
            lastedFocus?.requestFocus()
            menuSelection = true
            btn1.background = getDrawable(R.drawable.menu_bg_color_selector)
            btn2.background = getDrawable(R.drawable.menu_bg_color_selector)
            btn3.background = getDrawable(R.drawable.menu_bg_color_selector)
            btn4.background = getDrawable(R.drawable.menu_bg_color_selector)
        }
        if (menuSelection && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && event.action == KeyEvent.ACTION_DOWN) {
            btn1.background = null
            btn2.background = null
            btn3.background = null
            btn4.background = null
            v.setBackgroundColor(Color.WHITE)
            menuSelection = false
        }

        if (menuSelection) {
            Log.d("TAG", "menu Selected")
        } else {
            Log.d("TAG", "menu Un Selection")
            lastedFocus = v
        }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {

    }
}
