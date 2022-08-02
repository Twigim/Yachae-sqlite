package com.example.yachae_sqlite

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment


class CustomDialog: DialogFragment() {

    //lateinit var btn_close_dialog : Button
    //lateinit var goto_veg_type_info : TextView

    lateinit var veg_type_radioGroup : RadioGroup
    lateinit var veg_type : String

    lateinit var dbManager: DBManager

    lateinit var username : String
    lateinit var password : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbManager = DBManager(context)

//        username = arguments?.getString("username", "Default username").toString()
//        password = arguments?.getString("password", "Default password").toString()
//        Log.d("dialog save test", username)
//        Log.d("dialog save test", password)

//        this.arguments?.let {
//            username = it.getString("username", username)
//            password = it.getString("password", password)
//        }
        Log.d("argument test", arguments.toString())

        if (getArguments() != null) {
            val mArgs = arguments
            username= mArgs?.getString("username").toString()
            password= mArgs?.getString("password").toString()

        }

        Log.d("final test", username)
        Log.d("final test", password)







    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_corner)

//        username= arguments?.getString("username").toString()
//        password= arguments?.getString("password").toString()

//        username = arguments?.getString("username").toString()
//        password = arguments?.getString("password").toString()


        val view = inflater.inflate(R.layout.custom_dialog, null)
        return view

    }

    override fun onStart() {
        super.onStart()

        // dialog 크기 조절
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        //dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        // dialog 닫기
        //btn_close_dialog = dialog!!.findViewById(R.id.btn_close_dialog) as Button

//        btn_close_dialog.setOnClickListener {
//            //dialog!!.dismiss()
//        }


        // 채식 유형 정보 bottom sheet 보여주기
        //goto_veg_type_info = dialog!!.findViewById(R.id.goto_veg_type_info) as TextView

        val bottomSheetFragment = VegTypeBottomSheetFragment()
//        goto_veg_type_info.setOnClickListener {
//            bottomSheetFragment.show(childFragmentManager,bottomSheetFragment.tag )
//        }


        // 사용자 채식 유형 radiobutton 선택 시
        veg_type_radioGroup = view?.findViewById(R.id.veg_type_radioGroup) as RadioGroup

        veg_type_radioGroup.setOnCheckedChangeListener(OnCheckedChangeListener { group, checkedId -> // checkedId is the RadioButton selected
            when (checkedId) {
                R.id.rb_type1 -> veg_type = "비건"
                R.id.rb_type2 -> veg_type = "락토"
                R.id.rb_type3 -> veg_type = "오보"
                R.id.rb_type4 -> veg_type = "락토오보"
                R.id.rb_type5 -> veg_type = "폴로"
                R.id.rb_type6 -> veg_type = "페스코"
            }
            Toast.makeText(requireActivity(), veg_type + " 이(가) 선택됨", Toast.LENGTH_SHORT).show();

            dbManager.updateVegTypeData(username, password, veg_type)
            

            Log.d("final test", veg_type)



        })



    }


}
