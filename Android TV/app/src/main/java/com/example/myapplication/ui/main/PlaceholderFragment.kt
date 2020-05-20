package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main2, container, false)
//        val textView: TextView = root.findViewById(R.id.section_label)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)
        val recyclerView2: RecyclerView = root.findViewById(R.id.recycler_view2)
        val recyclerView3: RecyclerView = root.findViewById(R.id.recycler_view3)
//        pageViewModel.text.observe(this, Observer<String> {
//            textView.text = it
//        })

        recyclerView.layoutManager = LinearLayoutManager(container?.context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView2.layoutManager = LinearLayoutManager(container?.context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView3.layoutManager = LinearLayoutManager(container?.context,LinearLayoutManager.HORIZONTAL,false)

        recyclerView.adapter = MyListAdapter()
        recyclerView2.adapter = MyListAdapter()
        recyclerView3.adapter = MyListAdapter()

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
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
        return 20
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