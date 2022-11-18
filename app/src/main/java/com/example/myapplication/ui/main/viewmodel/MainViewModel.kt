package com.example.myapplication.ui.main.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.Fact
import com.example.myapplication.data.repository.MainRepository
import com.example.myapplication.utils.Resource

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val facts = MutableLiveData<Resource<List<Fact>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        facts.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getFacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ factList ->
                    facts.postValue(Resource.success(factList))
                }, { _ ->
                    facts.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getFacts(): LiveData<Resource<List<Fact>>> {
        return facts
    }

}