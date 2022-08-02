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
    var calendar = Calendar.getInstance(Locale.KOREAN)
    //lateinit var context: Context
    var dateFormat = SimpleDateFormat("yyyy MMMM", Locale.KOREAN)
    lateinit var myGridAdapter: MyGridAdapter
    lateinit var alertDialog: AlertDialog
    var dates: MutableList<Date> = ArrayList()
    var insert = false
    lateinit var chipName: String
    lateinit var chipGroup: ChipGroup
    lateinit  var dbopenhelper: DBOpenHelper
    lateinit var db: SQLiteDatabase

    lateinit var dbManager: DBManager

    var initChallenge : Boolean = false
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        IntializeLayout()
        SetUpCalendar()
        PreviousButton!!.setOnClickListener { //-1한 월을 넣어줌
            calendar.add(Calendar.MONTH, -1)
            SetUpCalendar()
        }
        NextButton!!.setOnClickListener { //+1한 월을 넣어줌
            calendar.add(Calendar.MONTH, 1)
            SetUpCalendar()
        }
        gridView!!.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val builder = AlertDialog.Builder(context)
                val addView =
                    LayoutInflater.from(parent.context).inflate(R.layout.add_newevent_layout, null)
                Log.d("Gridview", "Gridview 눌렸음")
                val AddEvent = addView.findViewById<Button>(R.id.addevent)
                val btn_close = addView.findViewById<Button>(R.id.btn_close_dialog)
                val chipgroup = addView.findViewById<ChipGroup>(R.id.chipgroup)
                val chip_milk = addView.findViewById<Chip>(R.id.chip_milk)
                val chip_dairyproduct = addView.findViewById<Chip>(R.id.chip_dairyproduct)
                val chip_vegetable = addView.findViewById<Chip>(R.id.chip_vegetable)
                val chip_egg = addView.findViewById<Chip>(R.id.chip_egg)
                val chip_fish = addView.findViewById<Chip>(R.id.chip_fish)
                val chip_meat = addView.findViewById<Chip>(R.id.chip_meat)
                val chip_fowls = addView.findViewById<Chip>(R.id.chip_fowls)
                builder.setView(addView)
                alertDialog = builder.create()
                alertDialog.show()
                AddEvent.isClickable = false
                chipName = chip_milk.text.toString()

                /*   chip_milk.setOnClickListener(new OnClickListener()
                        {
                            @Override
                            public void onClick(View v){
                                if(chip_milk.isChecked()==true){
                                Log.d("chip_milk","chip_milk 성공");
                                insert=true;
                                //insert=dbOpenHelper.insertData(chipName);
                                //텍스트로 insert}
                            }
                        });*/AddEvent.setOnClickListener(object : View.OnClickListener {
                var str_name = ""
                override fun onClick(v: View) {
                    Log.d("기록하기 버튼", "눌렸습니다.")
                    //SaveEvent(EventName.getText().toString(),EventName.getText().toString());
                    if (chipgroup.checkedChipId == R.id.chip_milk) {
                        Log.d("CHIP_MILK", "milk 눌림")
                        str_name = chip_milk.text.toString()
                    }
                    //db = dbopenhelper!!.writableDatabase
                    //db.execSQL("INSERT INTO chipName VALUES('$str_name');")
                    //db.close()
                    view.setBackgroundResource(R.drawable.stamp3black)
                    alertDialog.dismiss()
                }
            })
                btn_close.setOnClickListener {
                    Log.d("CLOSEBTN", "취소 버튼 눌림")
                    alertDialog.dismiss()
                }
            }
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    /*private void SaveEvent(String event, String date,String month,String year){
        dbOpenHelper=new DBOpenHelper(context);
        SQLiteDatabase database=dbOpenHelper.getWritableDatabase();
        dbOpenHelper.SaveEvent(event,date,month,year,database);
    }*/
    private fun IntializeLayout() {
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
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
        myGridAdapter = MyGridAdapter(dates, calendar)
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