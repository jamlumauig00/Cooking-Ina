package com.jam.cookingina

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController


class MainpageFragment : Fragment() {

    companion object {
        private const val ARG_DATA = "data"
        private const val ARG_DRAWABLE_NAME = "drawableName"

        fun newInstance(data: String?, drawableName: String): MainpageFragment {
            val fragment = MainpageFragment()
            val args = Bundle()
            args.putString(ARG_DATA, data)
            args.putString(ARG_DRAWABLE_NAME, drawableName)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var data: String
    private lateinit var drawableName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getString(ARG_DATA).toString()
            drawableName = it.getString(ARG_DRAWABLE_NAME).toString()
        }
    }

    @SuppressLint("MissingInflatedId", "DiscouragedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mainpage, container, false)
        Log.e("drawableName", drawableName)

        // Use the 'data' to populate the fragment's layout
        val textView = view.findViewById<TextView>(R.id.textview)
        textView.text = data

        val rlayout = view.findViewById<RelativeLayout>(R.id.rlayout)
        val resId = context?.resources?.getIdentifier(drawableName, "drawable", context?.packageName)
        Log.e("drawableName",resId.toString())

        if (resId != 0) {
            rlayout.setBackgroundResource(resId!!)
        }

        rlayout.setOnClickListener {
            // Handle the click event here
            //Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
            // If you want to navigate to another fragment, you can use Navigation component like this:
        }

        return view
    }
}
