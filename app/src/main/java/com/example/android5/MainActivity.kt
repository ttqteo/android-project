package com.example.android5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val scheduleFragment = ScheduleFragment()
    private val newsfeedFragment = NewsfeedFragment()
    private val notificationFragment = NotificationFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
    }
}