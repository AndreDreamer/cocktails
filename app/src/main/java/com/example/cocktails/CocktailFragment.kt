package com.example.cocktails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktails.databinding.FragmentCocktailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CocktailFragment : Fragment() {
    private val API_KEY = "1"
    private val BASE_URL = "https://thecocktaildb.com/"
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: FragmentCocktailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            refreshLayout.setOnRefreshListener {
                // Initialize a new Runnable
                var runnable = Runnable {
                    val retrofit: Retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val service = retrofit.create(CocktailApi::class.java)

                    val reposOrdinaryDrink = service.listCocktail(API_KEY)

                    reposOrdinaryDrink.enqueue(object : Callback<DrinkList> {
                        override fun onFailure(call: Call<DrinkList>, t: Throwable) {
                            Toast.makeText(
                                context,
                                t.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            refreshLayout.isRefreshing = false;
                        }

                        override fun onResponse(
                            call: Call<DrinkList>,
                            response: Response<DrinkList>
                        ) {
                            if (response.isSuccessful) {
                                linearLayoutManager = LinearLayoutManager(context)
                                recyclerView.layoutManager = linearLayoutManager
                                recyclerView.adapter =
                                    response.body()?.let { RecyclerViewAdapter(it.drinks) }

                            } else {
                                Log.d("Log:", "response code " + response.code())
                            }
                            refreshLayout.isRefreshing = false;
                        }


                    })
                }.run()
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = CocktailFragment()
    }
}

