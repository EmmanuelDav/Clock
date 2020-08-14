package com.example.alarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.alarm.Adapters.PagerAdapter
import com.example.alarm.Fragments.AlarmFragment
import com.example.alarm.Fragments.ClockFragment
import com.example.alarm.Fragments.StopWatchFragment
import com.example.alarm.Fragments.TimerFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        viewPager = findViewById(R.id.Pager)
        tabLayout = findViewById(R.id.tabLayout)
        //Adding tab icon
        tabLayout.addTab(tabLayout.newTab().setText("Alarm"))
        tabLayout.addTab(tabLayout.newTab().setText("Clock"))
        tabLayout.addTab(tabLayout.newTab().setText("Timer"))
        tabLayout.addTab(tabLayout.newTab().setText("Watch"))
        //Add fragments
        val pagerAdapter = PagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(AlarmFragment())
        pagerAdapter.addFragment(ClockFragment())
        pagerAdapter.addFragment(TimerFragment())
        pagerAdapter.addFragment(StopWatchFragment())
        //setting adapter
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mian_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settting -> {
                Toast.makeText(this,
                    "setting is not implemented yet",
                    Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}