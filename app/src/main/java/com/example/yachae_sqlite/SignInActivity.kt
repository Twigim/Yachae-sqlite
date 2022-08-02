package com.example.yachae_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.widget.Button
import android.widget.TextView

class SignInActivity : AppCompatActivity() {
    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var btn_signin : Button
    lateinit var tv_signup : TextView
    lateinit var dbManager: DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        username = findViewById(R.id.edt_username)
        password = findViewById(R.id.edt_password)
        btn_signin = findViewById(R.id.btn_signin)
        tv_signup = findViewById(R.id.tv_gotoSignup)

        dbManager = DBManager(this)

        btn_signin!!.setOnClickListener {
            val user = username!!.text.toString()
            val pass = password!!.text.toString()
            if (user == "" || pass == "") Toast.makeText(
                this@SignInActivity, "빈칸을 모두 입력해 주세요.",Toast.LENGTH_SHORT).show() else
                { val checkUserPass = dbManager!!.checkUsernamePassword(user, pass)
                    if (checkUserPass == true) {
                    Toast.makeText(this@SignInActivity, "로그인 성공!!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent) }
                    else {
                    Toast.makeText(this@SignInActivity, "로그인 실패..", Toast.LENGTH_SHORT).show()
                }
            }
        }

        tv_signup!!.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}