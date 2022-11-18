package com.example.myapplication.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getFacts() = apiService.getFacts()

}