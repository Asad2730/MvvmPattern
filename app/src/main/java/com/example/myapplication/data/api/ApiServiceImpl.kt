package com.example.myapplication.data.api
import com.example.myapplication.data.model.Fact
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getFacts(): Single<List<Fact>> {
        return Rx2AndroidNetworking.get("https://catfact.ninja/fact")
            .build()
            .getObjectListSingle(Fact::class.java)
    }

}
