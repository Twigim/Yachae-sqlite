package com.example.yachae_sqlite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment


class CustomDialog: DialogFragment() {

    lateinit var btn_close_dialog : Button
    lateinit var goto_veg_type_info : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.dialog_corner)


        return inflater.inflate(R.layout.custom_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        // dialog 닫기
        btn_close_dialog = dialog!!.findViewById(R.id.btn_close_dialog) as Button

        btn_close_dialog.setOnClickListener {
            dialog!!.dismiss()
        }

        // 채식 유형 정보 bottom sheet 보여주기기
        goto_veg_type_info = dialog!!.findViewById(R.id.goto_veg_type_info) as TextView

        val bottomSheetFragment = VegTypeBottomSheetFragment()
        goto_veg_type_info.setOnClickListener {
            bottomSheetFragment.show(childFragmentManager,bottomSheetFragment.tag )
        }

    }

}