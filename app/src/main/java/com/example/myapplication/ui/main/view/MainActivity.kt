package com.example.myapplication.ui.main.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.api.ApiHelper
import com.example.myapplication.data.api.ApiServiceImpl

import com.example.myapplication.data.model.Fact
import com.example.myapplication.ui.base.ViewModelFactory

import com.example.myapplication.ui.main.adapter.MainAdapter
import com.example.myapplication.ui.main.viewmodel.MainViewModel




class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private var recyclerView:RecyclerView = RecyclerView(this)
    var progressBar:ProgressBar = ProgressBar(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {

//        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {

        mainViewModel.getFacts().observe(this, Observer {
            when (it.status) {
                com.example.myapplication.utils.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                com.example.myapplication.utils.Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                com.example.myapplication.utils.Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }

        })
    }

    private fun renderList(users: List<Fact>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.apply {  ViewModelFactory(ApiHelper(ApiServiceImpl())) }
//       mainViewModel = ViewModelProviders(
//            this,
//            ViewModelFactory(ApiHelper(ApiServiceImpl()))
//      ).get(MainViewModel::class.java)

    }
}