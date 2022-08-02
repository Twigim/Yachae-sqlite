package com.example.yachae_sqlite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment


class ChallengeFragment : Fragment() {

    lateinit var dbManager: DBManager

    var initChallenge : Boolean = false


    lateinit var username : String
    lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        username = arguments?.getString("username").toString()
//        password = arguments?.getString("password").toString()
//
//        Log.d("save test", username.toString())
//        Log.d("save test", password.toString())

//        username = arguments?.getString("username").toString()
//        password = arguments?.getString("password").toString()







    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val username = arguments?.getString("username")
//        val password = arguments?.getString("password")
//
//        Log.d("save test", username.toString())
//        Log.d("save test", password.toString())


//        username= arguments?.getString("username").toString()
//        password= arguments?.getString("password").toString()

        var bundle = arguments
        username = bundle?.getString("username").toString()
        password = bundle?.getString("password").toString()

        Log.d("challenge saveu test", username)
        Log.d("challenge savep test", password)




        return inflater.inflate(R.layout.fragment_challenge, container, false)
    }

    override fun onStart() {
        super.onStart()




        dbManager = DBManager(context)
        //initChallenge = dbManager.isVegTypeNull("users")

        val MyDB = dbManager.writableDatabase
        val cursor = MyDB.rawQuery(
            "Select * from users where veg_type = ?", null)



        val dialogFragment = CustomDialog() // my custom FargmentDialog
        var args: Bundle? = null
        args?.putString("username", username);
        args?.putString("password", password);

        dialogFragment.setArguments(args)
        dialogFragment.show(parentFragmentManager, "CustomDialog")



        if (cursor.count != 0) {
            Toast.makeText(requireActivity(), "veg_type 존재", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireActivity(), "veg_type 존재하지 않음", Toast.LENGTH_SHORT).show()

//            val dialogFragment = CustomDialog()
//            val bundle = Bundle().apply {
//                putString("username", username)
//                putString("password", password)
//
//            }
//            dialogFragment.arguments = bundle

//            newInstance(username, password)
//            activity?.let { CustomDialog().show(it.supportFragmentManager, "CustomFragment") }


            
        }
    }






}