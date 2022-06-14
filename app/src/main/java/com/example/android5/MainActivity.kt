package com.example.android5

import android.app.FragmentManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.android5.databinding.ActivityMainBinding
import com.example.android5.home.HomeFragment
import com.example.android5.newstatus.NewFragment
import com.example.android5.notify.NotificationFragment
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
        val bundle = intent.extras

        if (bundle != null) {
            val getMssv = bundle.getString("mssv")
            binding.userName.text =  getMssv
        }

        supportActionBar?.hide()
        bottomNavView = binding.botNav

        replaceFragment(homeFragment,null)
        binding.logo.setOnClickListener {
            replaceFragment(homeFragment,null)
            bottomNavView.selectedItemId = R.id.home_bot
        }



        binding.avatar.setOnClickListener {
            if (bundle != null) {
                bundle.getString("mssv")?.let { it1 -> replaceFragment(profileFragment, it1) }
            }
            bottomNavView.selectedItemId = R.id.profile_bot
        }

        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_bot -> if (bundle != null) {
                    bundle.getString("mssv")
                        ?.let { it1 -> replaceFragment(homeFragment, it1) }
                }
                R.id.schedule_bot -> if (bundle != null) {
                    bundle.getString("mssv")
                        ?.let { it1 -> replaceFragment(scheduleFragment, it1) }
                }
                R.id.new_bot -> if (bundle != null) {
                    bundle.getString("mssv")
                    //val bundleforfeed = Bundle()
                    //val intent1 = Intent(this.requireContext(), NewFragment::class.java,)
                        ?.let { it1 -> replaceFragment(newFragment, it1) }
                }
                R.id.notification_bot -> if (bundle != null) {
                    bundle.getString("mssv")
                        ?.let { it1 -> replaceFragment(notificationFragment, it1) }
                }

                R.id.profile_bot ->if (bundle != null) {
                    bundle.getString("mssv")
                        ?.let { it1 -> replaceFragment(profileFragment, it1) }
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, getMissive: String?) {
        val transaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("mssv",getMissive)
        transaction.replace(R.id.container, fragment,bundle.toString())
        transaction.commit()
    }
}