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

        //다음달로 이동 버튼
        NextButton!!.setOnClickListener { //+1한 월을 넣어줌
            calendar.add(Calendar.MONTH, 1)
            SetUpCalendar() }

        //dbOpenHelper=DBOpenHelper(this,"chips",null,1)

        gridView!!.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val builder = AlertDialog.Builder(context)
                val addView =
                    LayoutInflater.from(parent.context).inflate(R.layout.add_newevent_layout, null)

                val AddEvent = addView.findViewById<Button>(R.id.addevent)
                val btn_close = addView.findViewById<Button>(R.id.btn_close_dialog)
                val chipgroup = addView.findViewById(R.id.chipgroup) as ChipGroup
                chip_milk = addView.findViewById(R.id.chip_milk)
                val chip_dairyproduct = addView.findViewById<Chip>(R.id.chip_dairyproduct)
                val chip_vegetable = addView.findViewById<Chip>(R.id.chip_vegetable)
                val chip_egg = addView.findViewById<Chip>(R.id.chip_egg)
                val chip_fish = addView.findViewById<Chip>(R.id.chip_fish)
                val chip_meat = addView.findViewById<Chip>(R.id.chip_meat)
                val chip_fowls = addView.findViewById<Chip>(R.id.chip_fowls)

                Log.d("Gridview", "Gridview 눌렸음")

                builder.setView(addView)
                alertDialog = builder.create()
                alertDialog.show()


                AddEvent.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View) {
                        /*
                           if (chip_milk.isChecked()==true) {
                               insert=dbOpenHelper.chipinsertData(chipname)
                               Log.d("CHIP_MILK", "milk 눌림")
                               *//*if(insert==true){
                        Toast.makeText(this@CustomCalendarActivity, "기록 성공!!", Toast.LENGTH_SHORT).show()}*//*
                }*/
                        Log.d("기록하기 버튼", "눌렸습니다.")
                        view.setBackgroundResource(R.drawable.stamp3black)
                        alertDialog.dismiss()
                    }
                })

                //취소 버튼 클릭시
                btn_close.setOnClickListener {
                    Log.d("CLOSEBTN", "취소 버튼 눌림")
                    alertDialog.dismiss()
                }
            }
    }

    /*private void SaveEvent(String event, String date,String month,String year){
        dbOpenHelper=new DBOpenHelper(context);
        SQLiteDatabase database=dbOpenHelper.getWritableDatabase();
        dbOpenHelper.SaveEvent(event,date,month,year,database);
    }*/
    private fun IntializeLayout() {
        val inflater =requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.calendar_layout, this)
        PreviousButton = view.findViewById(R.id.previousBtn)
        NextButton = view.findViewById(R.id.nextBtn)
        CurrentDate = view.findViewById(R.id.currnet_Date)
        gridView = view.findViewById(R.id.gridview)
    }

    private fun SetUpCalendar() {
        val currentDate = dateFormat.format(calendar.time)
        CurrentDate!!.text = currentDate
        dates.clear()
        //날짜 복사해서 변수 생성
        val monthCalendar = calendar.clone() as Calendar
        //1일로 셋팅
        monthCalendar[Calendar.DAY_OF_MONTH] = 1
        //요일 가져와서 -1=>일요일=1, 월요일=2...
        val FirstDayofMonth = monthCalendar[Calendar.DAY_OF_WEEK] - 1
        //날짜 셋팅
        monthCalendar.add(Calendar.DAY_OF_MONTH, -FirstDayofMonth)
        while (dates.size < MAX_CALENDAR_DAYS) {
            //리스트에 날짜 등록
            dates.add(monthCalendar.time)
            //1일씩 늘린 날짜로 변경
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        myGridAdapter = MyGridAdapter(context!!, dates, calendar)
        gridView!!.adapter = myGridAdapter
    }

    companion object {
        private const val MAX_CALENDAR_DAYS = 42
    }

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

        var dbManager = DBManager(context)
        initChallenge = dbManager!!.existsColumnInTable("users", "veg_type")

        if (initChallenge == true) {
            Toast.makeText(requireActivity(), "필드 있음", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireActivity(), "필드 존재하지 않음!!", Toast.LENGTH_SHORT).show()
            activity?.let { CustomDialog().show(it.supportFragmentManager, "CustomFragment") }
        }
    }


}