package com.example.yachae_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lateinit var btn_signin : Button
        lateinit var btn_signup : Button

        btn_signin = findViewById(R.id.btn_signin)
        btn_signup = findViewById(R.id.btn_signup)

        btn_signin.setOnClickListener {
            gotoSigninActivity()
        }

        btn_signup.setOnClickListener {
            gotoSignupActivity()
        }
    }

    private fun gotoSigninActivity(){
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun gotoSignupActivity(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }


}