package com.shanu.avengersstats

import Hero
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shanu.avengersstats.data.model.MainViewModel

class ListAdapter(private val viewModel: MainViewModel,private val listOfHeroes:List<Hero>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listOfHeroes.size
    }

}