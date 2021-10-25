package com.example.cocktails

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface CocktailApi {

    @GET("api/json/v1/{API_KEY}/filter.php?c=Ordinary_Drink")
    fun listOrdinaryDrink(@Path("API_KEY") apiKey: String): Call<DrinkList>

    @GET("api/json/v1/{API_KEY}/filter.php?c=Cocktail")
    fun listCocktail(@Path("API_KEY") apiKey: String): Call<DrinkList>

    @GET("api/json/v1/{API_KEY}/lookup.php?i={ID}")
    fun detailDrink(@Path("API_KEY") apiKey: String, @Path("ID") id: String): Call<Drink>

}