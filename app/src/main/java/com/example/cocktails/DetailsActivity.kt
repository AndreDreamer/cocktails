package com.example.cocktails

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cocktails.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : Activity() {
    private val BASE_URL = "https://thecocktaildb.com/"
    private val API_KEY = "1"
    val DRINK_KEY = "DRINK_KEY"
    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setupViews()
        setContentView(binding.root)
    }

    private fun setupViews() {
        with(binding) {

            buttonBack.setOnClickListener {
                finish()
            }

            Runnable {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(CocktailApi::class.java)
                val index = intent.getLongExtra(DRINK_KEY, 0)
                val reposDrink = service.detailDrink(API_KEY, index.toString())

                reposDrink.enqueue(object : Callback<DrinkDetailList> {
                    override fun onFailure(call: Call<DrinkDetailList>, t: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            t.toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    override fun onResponse(
                        call: Call<DrinkDetailList>,
                        response: Response<DrinkDetailList>
                    ) {
                        if (response.isSuccessful) {
                            val currentDrink = response.body()?.drinks?.get(0)
                            Picasso.with(titleTextView.context)
                                .load(currentDrink?.image)
                                .into(imageView)
                            titleTextView.text = currentDrink?.name
                            instructionTextView.text = currentDrink?.instruction
                            Log.d("Log", response.body()?.drinks?.size.toString())
                        } else {
                            Log.d("Log:", "response code " + response.code())
                        }
                    }


                })
            }.run()


        }
    }

}