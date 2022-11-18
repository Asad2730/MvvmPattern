package com.example.myapplication.data.api
import com.example.myapplication.data.model.Fact
import io.reactivex.Single

interface  ApiService {

    fun getFacts(): Single<List<Fact>>
}