package com.example.hiltretrofitincompose.model

import java.io.Serializable

data class Products(
    val limit: Int=0,
    val products: List<Product>?=null,
    val skip: Int=0,
    val total: Int=0
):Serializable