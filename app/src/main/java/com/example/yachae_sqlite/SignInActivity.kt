package com.example.yachae_sqlite

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kapil.kotlindemo.DBManager

class SignInActivity : AppCompatActivity() {

    var email :String =""
    var password : String = ""
    var checkPassword : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        var edt_email = findViewById(R.id.edt_email) as EditText
        var edt_password = findViewById(R.id.edt_password) as EditText
        var btn_signin : Button = findViewById(R.id.btn_signin) as Button
        var tv_gotoSignup = findViewById(R.id.btn_gotoSignup) as TextView
        var databaseHelper = DBManager(this)

        tv_gotoSignup.setOnClickListener { v: View? ->
            var intent : Intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        btn_signin.setOnClickListener { v: View? ->
            email = edt_email.text.toString()
            password = edt_password.text.toString()


            if (email.isEmpty()){
                edt_email.setError("이메일을 입력해 주세요")
                return@setOnClickListener
            }
            else if(password.isEmpty()){
                edt_password.setError("비밀번호를 입력해 주세요.")
                return@setOnClickListener
            }
            else  {

                var lQuerry: String = "Select * from " + databaseHelper.tableName + " where " + databaseHelper.EMAIL + "='" + email + "' and " + databaseHelper.PASSWORD + "='" + password + "'"
                var cursor: Cursor = databaseHelper.GetDetaislCursor(lQuerry)

                if (cursor.count > 0) {
                    var intent: Intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("이메일", email)
                    startActivity(intent)
                    finish()
                }

                else
                    Toast.makeText(this,"이메일과 비밀번호를 올바르게 입력해 주세요.", Toast.LENGTH_LONG).show()
            }

        }



    }
}