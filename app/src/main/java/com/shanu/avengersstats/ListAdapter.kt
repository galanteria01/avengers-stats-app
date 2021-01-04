package com.shanu.avengersstats

import Hero
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shanu.avengersstats.data.model.MainViewModel

class ListAdapter(private val viewModel: MainViewModel,private val listOfHeroes:List<Hero>):RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val myView = LayoutInflater.from(parent.context).inflate(R.layout.avengers_ticket,parent,false)
        return ViewHolder(myView)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        return holder.bind(listOfHeroes[position])
    }

    override fun getItemCount(): Int {
        return listOfHeroes.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val heroName = itemView.findViewById<TextView>(R.id.tvName)
        fun bind(hero:Hero){
            heroName.text = hero.name.toString()

        }
    }

}