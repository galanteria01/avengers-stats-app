package com.shanu.avengersstats

import android.media.Image

class Hero(var response:String, var id:Int, var name:String, var powerstats:List<Power>,
            var biography:List<Bio>, var appearance:List<Appear>,var work:List<Work>
           ,var connection:List<ConnectionHero>,var image:List<Image> ) {

}

class Power(var intelligence:Int,var strength:Int,var speed:Int,var durability:Int,var power:Int,var combat:Int){

}

class Bio(var fullName:String,var alterEgo:String,var aliases:List<String>
          ,var placeOfBirth:String,var firstAppearance:String
          ,var publisher:String,var alignment:String){

}

class Appear(var gender:String,var race:String,var height:List<String>
             ,var weight:List<String>,var eyeColor:String,var hairColor:String){

}

class Work(var occupation:String,var base:String){

}

class ConnectionHero(var groupAffiliation:String,var relatives:String){

}

class Image(var url:String){

}