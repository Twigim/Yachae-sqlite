package com.example.yachae_sqlite

import android.app.AlertDialog
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.text.SimpleDateFormat
import java.util.*

class ChallengeFragment: Fragment {
    lateinit var NextButton: Button
    lateinit var PreviousButton: Button
    lateinit var CurrentDate: TextView
    lateinit var gridView: GridView
    lateinit var dbOpenHelper: DBOpenHelper
    var calendar = Calendar.getInstance(Locale.KOREAN)
    var dateFormat = SimpleDateFormat("yyyy MMMM", Locale.KOREAN)
    lateinit var myGridAdapter: MyGridAdapter
    lateinit var alertDialog: AlertDialog
    lateinit var chip_name : String
    lateinit var chip_milk:Chip
    var dates: MutableList<Date> = ArrayList()

    var chip:Boolean=false

    var insert:Boolean = false
    lateinit var chipname: String

    var initChallenge : Boolean = false
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        IntializeLayout()
        SetUpCalendar()

        //전달로 이동 버튼
        PreviousButton!!.setOnClickListener { //-1한 월을 넣어줌
            calendar.add(Calendar.MONTH, -1)
            SetUpCalendar() }

    lateinit var username : String
    lateinit var password : String

    lateinit var veg_type : String

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

        veg_type = arguments?.getString("veg_type").toString()

        Log.d("challenge saveb test", veg_type.toString())



        return inflater.inflate(R.layout.fragment_challenge, container, false)
    }

    override fun onStart() {
        super.onStart()

        var dbManager = DBManager(context)
        initChallenge = dbManager!!.existsColumnInTable("users", "veg_type")



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
        dialogFragment.arguments
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
              activity?.let { CustomDialog().show(it.supportFragmentManager, "CustomFragment") }


            
        }
    }






}