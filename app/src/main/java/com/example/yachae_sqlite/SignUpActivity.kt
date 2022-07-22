package com.example.yachae_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var passwordCheck: EditText

    lateinit var signup : Button

    lateinit var DB : DBManager

    lateinit var user : String
    lateinit var pass : String
    lateinit var passCheck : String

    var checkUser: Boolean = false
    var insert: Boolean = false



    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_signup)

        username = findViewById(R.id.edt_username)
        password = findViewById(R.id.edt_password)
        passwordCheck = findViewById(R.id.edt_passwordCheck)

        signup = findViewById(R.id.btn_signup)

        DB = DBManager(this);

        signup.setOnClickListener {
            user = username.text.toString()
            pass = password.text.toString()
            passCheck = passwordCheck.text.toString()

            if(user.equals("") || pass.equals("") || passCheck.equals(""))
                Toast.makeText(this@SignUpActivity, "빈칸을 모두 입력해 주세요.", Toast.LENGTH_SHORT).show()
            else{
                if(pass.equals(passCheck)){
                    checkUser = DB.checkUsername(user)
                    if(checkUser == false){
                        insert = DB.insertData(user, pass)
                        if(insert == true){
                            Toast.makeText(this@SignUpActivity, "회원가입 성공!!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this@SignUpActivity, "회원가입 실패..", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this@SignUpActivity, "이미 존재하는 회원정보입니다.", Toast.LENGTH_SHORT).show()

                    }
                }else{
                    Toast.makeText(this@SignUpActivity, "입력한 정보를 다시 확인해 주세요", Toast.LENGTH_SHORT).show()

                }
            }

        }
    }
}