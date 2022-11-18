package com.example.myapplication.data.repository
import com.example.myapplication.data.api.ApiHelper
import com.example.myapplication.data.model.Fact
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getFacts(): Single<List<Fact>> {
        return apiHelper.getFacts()
    }

}