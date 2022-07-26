package com.example.yachae_sqlite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


class ChallengeFragment : Fragment() {

    lateinit var dbManager: DBManager

    var initChallenge : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_challenge, container, false)
    }

    override fun onStart() {
        super.onStart()

        dbManager = DBManager(context)
        initChallenge = dbManager!!.existsColumnInTable("users", "veg_type")

        if (initChallenge == true) {
            Toast.makeText(requireActivity(), "필드 있음", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireActivity(), "필드 존재하지 않음!!", Toast.LENGTH_SHORT).show()
            activity?.let { CustomDialog().show(it.supportFragmentManager, "CustomFragment") }
        }
    }


}