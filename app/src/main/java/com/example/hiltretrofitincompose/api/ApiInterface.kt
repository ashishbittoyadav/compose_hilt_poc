package com.example.hiltretrofitincompose.api

import com.example.hiltretrofitincompose.model.Products
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/products")
    suspend fun getResponse():Response<Products>
}