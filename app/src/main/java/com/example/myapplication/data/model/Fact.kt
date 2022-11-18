package com.example.myapplication.data.model
import com.google.gson.annotations.SerializedName

 data class Fact (
    @SerializedName("fact")
    val name: String = "",
    @SerializedName("length")
    val length: Int = 0
 )