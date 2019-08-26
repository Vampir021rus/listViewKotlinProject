package com.example.listviewkotlinproject

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface MassageAPI{
@GET("messages1.json")
fun getMassage():Call<List<Massage>>
}