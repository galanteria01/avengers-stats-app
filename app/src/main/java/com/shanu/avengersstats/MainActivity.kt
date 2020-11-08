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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.avengers_ticket.*
import kotlinx.android.synthetic.main.avengers_ticket.view.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.EnumSet.range

class MainActivity() : AppCompatActivity(), Parcelable {
    lateinit var progress: ProgressBar
    var listOfAvengers = ArrayList<Avengers>()
    var adapter:AvengersAdapter?=null
    val client = OkHttpClient()

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dummyData()
        progress = findViewById(R.id.progressBar)
        progress.visibility = View.VISIBLE

        // start loading avengers
        run("https://superheroapi.com/api/access-token/character-id")


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
                intent.putExtra("subtitle",avenger.subtitle)
                context!!.startActivity(intent)

            }
            return myView

        }
    }

    fun dummyData(){
        listOfAvengers.add(
            Avengers("Iron Man","Leader of Avengers"
                ,R.drawable.ironman,R.string.ironman.toString()))
        listOfAvengers.add(
            Avengers("Thor","God of Thunder"
                ,R.drawable.thor,R.string.thor.toString()))
        listOfAvengers.add(
            Avengers("Hulk","The gamma machine"
                ,R.drawable.hulk,R.string.hulk.toString()))
        listOfAvengers.add(
            Avengers("Hawkeye","Unfuckwithable archer"
                ,R.drawable.hawkeye,R.string.hawkeye.toString()))
        listOfAvengers.add(
            Avengers("Captain America","Super Soldier"
                ,R.drawable.captain,R.string.captain.toString()))
        listOfAvengers.add(
            Avengers("Black Widow","Shield Spy"
                ,R.drawable.widow,R.string.widow.toString()))
    }

    fun run(url:String){
        progress.visibility = View.VISIBLE
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                progress.visibility = View.GONE

            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body!!.string()
                //creating json object
                val json_contact: JSONObject = JSONObject(str_response)
                //creating json array
                var jsonarray_info: JSONArray = json_contact.getJSONArray("info")
                var i:Int = 0
                var size:Int = jsonarray_info.length()
                listOfAvengers= ArrayList<Avengers>();
                for (i in 0.. size-1) {
                    var json_objectdetail:JSONObject=jsonarray_info.getJSONObject(i)
                    val id=json_objectdetail.getString("id")
                    val name=json_objectdetail.getString("name")
                    val email=json_objectdetail.getString("email")
                    var avenger = Avengers(name,id,12,email)
                    listOfAvengers.add(avenger)
                }
        }

            )}
}

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }