package com.example.yachae_sqlite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class VegInfoFragment : Fragment() {

    lateinit var goto_dachae : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        goto_dachae = requireView().findViewById(R.id.goto_dachae)
//
//        goto_dachae.setOnClickListener {
//            val intent = Intent(activity, OnlineMall::class.java)
//            startActivity(intent)
//        }

        return inflater.inflate(R.layout.fragment_veg_info, container, false)
    }

}