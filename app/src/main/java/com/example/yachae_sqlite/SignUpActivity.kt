package com.example.yachae_sqlite

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.kapil.kotlindemo.DBManager


class SignUpActivity : AppCompatActivity() {

    var email :String =""
    var password : String =""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var databaseHelper : DBManager = DBManager(this)
        var contentValues :ContentValues = ContentValues()

        var edt_email = findViewById(R.id.edt_email) as EditText
        var edt_password = findViewById(R.id.edt_password) as EditText



        var btn_signup : Button = findViewById(R.id.btn_signup) as Button

        btn_signup.setOnClickListener { v: View? ->


            email = edt_email.text .toString()
            password = edt_password.text .toString()

            if (!email.isEmpty() && !password.isEmpty()  ){

                var lQuerry : String = "select ${databaseHelper.EMAIL} from ${databaseHelper.tableName}"
                var cursor : Cursor = databaseHelper.GetDetaislCursor(lQuerry)
                if (cursor.count>0)
                    edt_email.setError("Email is already registered")
                else {

                    contentValues.put(databaseHelper.EMAIL, email)
                    contentValues.put(databaseHelper.PASSWORD, password)

                    databaseHelper.insertIntoTable(databaseHelper.tableName, contentValues)
                    Toast.makeText(this, "Registerd Succesfully", Toast.LENGTH_LONG).show()

                    var intent: Intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            else Toast.makeText(this, "fill all field", Toast.LENGTH_LONG).show()

        }
    }

}
