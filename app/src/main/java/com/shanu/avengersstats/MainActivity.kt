package com.shanu.avengersstats

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ProgressBar
import androidx.core.content.ContextCompat.startActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
    var listOfAvengers = ArrayList<Avengers>()
    var listOfHeroes = ArrayList<Hero>()
    var adapter: AvengersAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dummyData()
        adapter = AvengersAdapter(this, listOfAvengers)
        tvAvengers.adapter = adapter
        fetchJson()
    }

    fun fetchJson(){
        val url = "https://superheroapi.com/api/2720451141562745/70"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body!!.string()
                println(body)
                val gson = GsonBuilder().create()
                val collection = gson.fromJson(body,Hero::class.java)
                val name = collection.name
                val response = collection.response
                var id = collection.id

                var intelligence = collection.powerstats[0].intelligence
                var strength = collection.powerstats[1].strength
                val speed = collection.powerstats[2].speed
                var durability = collection.powerstats[3].durability
                val power = collection.powerstats[4].power
                var combat = collection.powerstats[5].combat
                var powerstats = Power(intelligence,strength,speed,durability,power,combat)
                val fullName = collection.biography[0].fullName
                val alterEgo = collection.biography[0].fullName



                var image = collection.
                if(response == "success") {
                    listOfHeroes.add(Hero(response, id, name, null,))
                }

            }

        })
    }


    class AvengersAdapter : BaseAdapter {
        var listOfAvengers = ArrayList<Avengers>()
        var context: Context? = null

        constructor(context: Context, listOfAvengers: ArrayList<Avengers>) : super() {
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
            var inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView = inflater.inflate(R.layout.avengers_ticket, null)
            myView.tvName.text = avenger.name!!
            myView.tvDes.text = avenger.des!!
            myView.ivBar.setImageResource(avenger.image!!)
            myView.ticketAvenger.setOnClickListener {
                val intent = Intent(context, moreAbout::class.java)
                intent.putExtra("name", avenger.name!!)
                intent.putExtra("des", avenger.des!!)
                intent.putExtra("image", avenger.image!!)
                intent.putExtra("subtitle", avenger.subtitle)
                context!!.startActivity(intent)

            }
            return myView

        }
    }

    fun dummyData() {
        listOfAvengers.add(
            Avengers(
                "Iron Man", "Leader of Avengers", R.drawable.ironman, R.string.ironman.toString()
            )
        )
        listOfAvengers.add(
            Avengers(
                "Thor", "God of Thunder", R.drawable.thor, R.string.thor.toString()
            )
        )
        listOfAvengers.add(
            Avengers(
                "Hulk", "The gamma machine", R.drawable.hulk, R.string.hulk.toString()
            )
        )
        listOfAvengers.add(
            Avengers(
                "Hawkeye", "Unfuckwithable archer", R.drawable.hawkeye, R.string.hawkeye.toString()
            )
        )
        listOfAvengers.add(
            Avengers(
                "Captain America", "Super Soldier", R.drawable.captain, R.string.captain.toString()
            )
        )
        listOfAvengers.add(
            Avengers(
                "Black Widow", "Shield Spy", R.drawable.widow, R.string.widow.toString()
            )
        )
    }
}

