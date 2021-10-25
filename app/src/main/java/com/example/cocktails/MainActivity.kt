package com.example.cocktails


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.cocktails.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : FragmentActivity() {

    private val numberOfTabs = 2
    private val BASE_URL = "https://thecocktaildb.com/"
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

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(CocktailApi::class.java)


            val reposOrdinaryDrink = service.listOrdinaryDrink()

            reposOrdinaryDrink.enqueue(object : Callback<DrinkList> {
                override fun onFailure(call: Call<DrinkList>, t: Throwable) {
                    Log.d("Log: Error!!!!!!!!!!!!! ", t.toString())
                }

                override fun onResponse(call: Call<DrinkList>, response: Response<DrinkList>) {
                    if (response.isSuccessful) {
                        Log.d("Log:", "response Size " + response.body()?.group?.lastIndex);
                    } else {
                        Log.d("Log:", "response code " + response.code());
                    }
                }


            })


        }
    }

}