package com.shanu.avengersstats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.avengers_ticket.*
import kotlinx.android.synthetic.main.avengers_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAvengers = ArrayList<Avengers>()
    var adapter:AvengersAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // start loading avengers
        listOfAvengers.add(Avengers("Iron Man","Leader of Avengers",R.drawable.ironman))
        listOfAvengers.add(Avengers("Thor","God of Thunder",R.drawable.thor))
        listOfAvengers.add(Avengers("Hulk","The gamma machine",R.drawable.hulk))
        listOfAvengers.add(Avengers("Hawkeye","Unfuckwithable archer",R.drawable.hawkeye))
        listOfAvengers.add(Avengers("Captain America","Super Soldier",R.drawable.captain))
        listOfAvengers.add(Avengers("Black Widow","Shield Spy",R.drawable.widow))


        adapter = AvengersAdapter(listOfAvengers)
    }
    class AvengersAdapter:BaseAdapter{
        var listOfAvengers = ArrayList<Avengers>()

        constructor(listOfAvengers:ArrayList<Avengers>):super(){
            this.listOfAvengers = listOfAvengers

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

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val avenger = listOfAvengers[position]
            var myView = layoutInflater.inflate(R.layout.avengers_ticket,null)
            myView.tvName.text = avenger.name
            myView.tvDes.text = avenger.des
            myView.ivBar.setImageResource(avenger.image)

        }
    }
}