package com.shanu.avengersstats

object Model {
    data class Result(val response:String, val id:String, val name:String, val full_name:String
    ,val alter_ego:String, val aliases:List<String>, val place_of_birth:String,val first_appearance:String
                      ,val publisher:String,val alignment:String)
}