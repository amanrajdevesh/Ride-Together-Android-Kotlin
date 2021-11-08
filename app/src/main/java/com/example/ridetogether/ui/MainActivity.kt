package com.example.ridetogether.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.ridetogether.R
import com.example.ridetogether.adapter.RideViewPagerAdapter
import com.example.ridetogether.databinding.ActivityMainBinding
import com.example.ridetogether.ui.fragments.AddFragment
import com.example.ridetogether.ui.fragments.SearchFragment
import com.example.ridetogether.ui.fragments.UserFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        if(auth.currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        val tabList = listOf<String>("Search","Add","User")
        val list = ArrayList<Fragment>()
        list.addAll(listOf(SearchFragment(),AddFragment(),UserFragment()))
        binding.viewPager.adapter = RideViewPagerAdapter(supportFragmentManager , lifecycle , list)
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()

    }
}