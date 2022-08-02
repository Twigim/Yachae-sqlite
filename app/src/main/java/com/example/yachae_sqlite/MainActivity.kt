package com.example.yachae_sqlite

import android.os.Bundle
import android.view.Menu
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

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
    //postactivity에서 화면이 전환된 경우 커뮤니티 화면으로 이동
    override fun onResume() {
        super.onResume()

        val targetFragment = intent.getStringExtra("Fragment")
        if(targetFragment == "postActivity") {
            supportFragmentManager.beginTransaction().replace(frameLayout.id, CommunityFragment()).commit()
        }
    }
}


