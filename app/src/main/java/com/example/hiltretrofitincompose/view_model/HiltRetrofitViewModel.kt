package com.example.hiltretrofitincompose.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltretrofitincompose.api.ApiInterface
import com.example.hiltretrofitincompose.model.Product
import com.example.hiltretrofitincompose.model.Products
import com.example.hiltretrofitincompose.room.ProductDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class HiltRetrofitViewModel @Inject constructor(private val savedStateHandle:SavedStateHandle,private val apiInterface: ApiInterface,private val productDao: ProductDao):ViewModel() {


    private var _products = MutableStateFlow(Products())
    val products = _products

    private val _currentProduct = MutableStateFlow<Product?>(null)

    init {
        viewModelScope.launch {
            getResponse()
        }
    }

    private suspend fun getResponse(){
        productDao.getProducts().let {
            if (it.isNullOrEmpty()){
                apiInterface.getResponse()
                    .let {
                        if (it.isSuccessful ){
                            it.body()?.let { products ->
                                _products.value = products
                                products.products?.forEach { product ->
                                    productDao.insertProduct(product)
                                }
                            }
                        }
                    }
            }else{
                _products.value = Products(products = it)
            }
        }
    }
}