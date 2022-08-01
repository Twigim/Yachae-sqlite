package com.example.yachae_sqlite

import android.os.Bundle
import android.view.Menu
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var username : String
    lateinit var password : String

    private val frameLayout: FrameLayout by lazy {
        findViewById(R.id.mainContainer)
    }

    private val bottomNavigation: BottomNavigationView by lazy {
        findViewById(R.id.bottomNavigationView)
    }

    var bottomNavigationView: BottomNavigationView? = null
    var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        username = intent.getStringExtra("user").toString()
        password = intent.getStringExtra("pass").toString()

        //This code is to pass the value to Fragment
        var bundle=Bundle()
        bundle.putString("username",username)
        var frag=ChallengeFragment()
        frag.arguments=bundle

        supportFragmentManager.beginTransaction().add(frameLayout.id, ChallengeFragment()).commit()




        bottomNavigation.setOnNavigationItemSelectedListener {
            replaceFragment(
                when (it.itemId) {
                    R.id.challenge_btn -> ChallengeFragment()
                    R.id.community_btn -> CommunityFragment()
                    R.id.vegInfo_btn -> VegInfoFragment()
                    else -> MypageFragment()
                }
            )
            true
        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView?.itemIconTintList = null
        menu = bottomNavigationView?.menu
        bottomNavigationView?.setSelectedItemId(R.id.challenge_btn) //선택된 아이템 지정
    }

    private fun replaceFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().replace(frameLayout.id, fragment).commit()

    }





}


