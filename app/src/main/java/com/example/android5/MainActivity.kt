package com.example.android5

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.android5.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val scheduleFragment = ScheduleFragment()
    private val newFragment = NewFragment()
    private val notificationFragment = NotificationFragment()
    private val profileFragment = ProfileFragment()

    private lateinit var bottomNavView: BottomNavigationView
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        bottomNavView = binding.botNav

        replaceFragment(homeFragment)
        binding.logo.setOnClickListener {
            replaceFragment(homeFragment)
            bottomNavView.selectedItemId = R.id.home_bot
        }
        binding.avatar.setOnClickListener {
            replaceFragment(profileFragment)
            bottomNavView.selectedItemId = R.id.profile_bot
        }

        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_bot -> replaceFragment(homeFragment)
                R.id.schedule_bot -> replaceFragment(scheduleFragment)
                R.id.new_bot -> replaceFragment(newFragment)
                R.id.notification_bot -> replaceFragment(notificationFragment)
                R.id.profile_bot -> replaceFragment(profileFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}