package com.example.cocktails

import retrofit2.http.GET

interface CocktailApi {


    @GET("api/json/v1/1/filter.php?c=Ordinary_Drink")
    fun listOrdinaryDrink()

}