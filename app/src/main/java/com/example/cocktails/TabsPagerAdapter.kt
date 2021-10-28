package com.example.cocktails

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                // Ordinary Drink
                val bundle = Bundle()
                bundle.putString("fragmentName", "Ordinary Drink Fragment")
                val ordinaryDrinkFragment = OrdinaryDrinkFragment()
                ordinaryDrinkFragment.arguments = bundle
                return ordinaryDrinkFragment
            }
            1 -> {
                // Cocktails
                val bundle = Bundle()
                bundle.putString("fragmentName", "Cocktails Fragment")
                val cocktailFragment = CocktailFragment()
                cocktailFragment.arguments = bundle
                return cocktailFragment
            }

            else -> return OrdinaryDrinkFragment()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}