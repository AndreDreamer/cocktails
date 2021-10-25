package com.example.cocktails

import com.google.gson.annotations.SerializedName

data class Drink(
    @SerializedName("idDrink") val id: Long,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkThumb") val image: String

)

data class DrinkList(
    @SerializedName("drinks")
    val drinks: List<Drink>
)