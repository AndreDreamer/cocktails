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

data class DrinkDetailList(
    @SerializedName("drinks")
    val drinks: List<DrinkDetail>
)

data class DrinkDetail(
    @SerializedName("idDrink") val id: Long,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkThumb") val image: String,
    @SerializedName("strInstructions") val instruction: String

)