package com.example.cocktails


import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.cocktails.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {

    private val numberOfTabs = 2
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupViews()
        setContentView(binding.root)

    }

    private fun setupViews() {
        with(binding) {
            //setup Tabs Pager
            val adapter = TabsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
            tabsViewpager.adapter = adapter
            // Link the TabLayout and the ViewPager2 together and Set Text
            TabLayoutMediator(tabLayout, tabsViewpager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Ordinary drink"
                    1 -> tab.text = "Cocktails"
                }
            }.attach()

        }


    }

}