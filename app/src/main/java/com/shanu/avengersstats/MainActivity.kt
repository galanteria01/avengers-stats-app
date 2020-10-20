package com.shanu.avengersstats

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.avengers_ticket.*
import kotlinx.android.synthetic.main.avengers_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAvengers = ArrayList<Avengers>()
    var adapter:AvengersAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // start loading avengers
        listOfAvengers.add(
            Avengers("Iron Man","Leader of Avengers",R.drawable.ironman,false))
        listOfAvengers.add(
            Avengers("Thor","God of Thunder",R.drawable.thor,false))
        listOfAvengers.add(
            Avengers("Hulk","The gamma machine",R.drawable.hulk,false))
        listOfAvengers.add(
            Avengers("Hawkeye","Unfuckwithable archer",R.drawable.hawkeye,true))
        listOfAvengers.add(
            Avengers("Captain America","Super Soldier",R.drawable.captain,false))
        listOfAvengers.add(
            Avengers("Black Widow","Shield Spy",R.drawable.widow,true))


        adapter = AvengersAdapter(this,listOfAvengers)
        tvAvengers.adapter = adapter
    }
    class AvengersAdapter:BaseAdapter{
        var listOfAvengers = ArrayList<Avengers>()
        var context:Context?=null

        constructor(context:Context,listOfAvengers:ArrayList<Avengers>):super(){
            this.listOfAvengers = listOfAvengers
            this.context = context

        }

        override fun getCount(): Int {
            return listOfAvengers.size
        }

        override fun getItem(position: Int): Any {
            return listOfAvengers[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        @SuppressLint("ViewHolder", "InflateParams")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val avenger = listOfAvengers[position]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView = inflater.inflate(R.layout.avengers_ticket,null)
            myView.tvName.text = avenger.name!!
            myView.tvDes.text = avenger.des!!
            myView.ivBar.setImageResource(avenger.image!!)
            myView.ticketAvenger.setOnClickListener {
                val intent = Intent(context,moreAbout::class.java)
                intent.putExtra("name",avenger.name!!)
                intent.putExtra("des",avenger.des!!)
                intent.putExtra("image",avenger.image!!)
                context!!.startActivity(intent)

            }
            return myView

        }
    }
}