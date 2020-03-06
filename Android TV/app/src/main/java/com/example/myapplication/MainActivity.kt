package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.menu_item.view.*


class MainActivity : FragmentActivity() {
    var TAG = "MainActivity"

    var currentMenu = -1
    var menuSelection = true
    var menuFocus = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        menuContainer.setOnFocusChangeListener { v, hasFocus ->
//            Log.d(TAG, "menuContainer hasFocus $hasFocus")
//            if (hasFocus) motionLayout.transitionToStart() else motionLayout.transitionToEnd()
//        }


        button1.requestFocus()
        currentMenu = button1.id





        contentContainertv.setOnFocusChangeListener { v, hasFocus ->
            Log.d(TAG, "contentContainertv hasFocus $hasFocus")
            if (hasFocus) motionLayout.transitionToEnd() else motionLayout.transitionToStart()

            val color =
                if (hasFocus) android.R.color.darker_gray else android.R.color.holo_blue_dark
            contentContainer1.background = getDrawable(color)

            if (hasFocus) {
                contentContainer1.nextFocusLeftId = currentMenu
            }
        }

        button1.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) currentMenu = button1.id
            menuSelection = hasFocus
        }

        button2.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) currentMenu = button2.id
            menuSelection = hasFocus
        }

        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                Log.d(TAG, " p1 $p1 $p2 $p3")
//                tvMenu1.alpha = 1 - p3
//                tvMenu2.alpha = 1 - p3
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

                if (menuFocus) {
                    button1.tv_menu.visibility = View.VISIBLE
                    button2.tv_menu.visibility = View.VISIBLE
                } else {
                    button1.tv_menu.visibility = View.GONE
                    button2.tv_menu.visibility = View.GONE
                }
            }

        })




        recyclerView1.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerView1.adapter = MyListAdapter()
        recyclerView2.adapter = MyListAdapter()


    }
}

class MyListAdapter :
    RecyclerView.Adapter<MyListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.textView.setText("position $position")
        holder.relativeLayout.setOnClickListener { view ->
            Toast.makeText(
                view.context,
                "click on item: $position",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView: TextView
        var relativeLayout: RelativeLayout

        init {
            imageView = itemView.findViewById<View>(R.id.imageView) as ImageView
            textView = itemView.findViewById<View>(R.id.textView) as TextView
            relativeLayout =
                itemView.findViewById<View>(R.id.relativeLayout) as RelativeLayout


        }
    }
}
