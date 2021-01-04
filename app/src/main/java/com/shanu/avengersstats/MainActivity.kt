package com.shanu.avengersstats

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ProgressBar
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shanu.avengersstats.data.model.MainViewModel
import com.shanu.avengersstats.data.model.MainViewModelFactory
import com.shanu.avengersstats.data.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.avengers_ticket.*
import kotlinx.android.synthetic.main.avengers_ticket.view.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URI.create
import java.util.EnumSet.range

class MainActivity() : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    var adapter:ListAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getHero()
        viewModel.myHeroData.observe(this, Observer { response ->
            if(response.isSuccessful){
                Log.d("avenger",response.body()!!.toString())


            }
        })
    }
}

