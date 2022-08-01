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


    lateinit var username : String
    lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        username = arguments?.getString("username").toString()
//        password = arguments?.getString("password").toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        username= arguments?.getString("username").toString()
        password= arguments?.getString("password").toString()

        val bundle = Bundle()
        bundle.putString("username", username)
        bundle.putString("password", password)


        return inflater.inflate(R.layout.fragment_challenge, container, false)
    }

    override fun onStart() {
        super.onStart()




        dbManager = DBManager(context)
        //initChallenge = dbManager.isVegTypeNull("users")

        val MyDB = dbManager.writableDatabase
        val cursor = MyDB.rawQuery(
            "Select * from users where veg_type = ?", null)

        if (cursor.count != 0) {
            Toast.makeText(requireActivity(), "veg_type 존재", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireActivity(), "veg_type 존재하지 않음", Toast.LENGTH_SHORT).show()





            activity?.let { CustomDialog().show(it.supportFragmentManager, "CustomFragment") }
        }
    }


}