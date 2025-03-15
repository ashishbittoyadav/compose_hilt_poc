package com.example.hiltretrofitincompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable


@Entity(tableName = "product")
data class Product(
    val availabilityStatus: String,
    val brand: String?=null,
    val category: String,
    val description: String,
    val dimensions: Dimensions,
    val discountPercentage: Double,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val images: List<String>,
    val meta: Meta,
    val minimumOrderQuantity: Int,
    val price: Double,
    val rating: Double,
    val returnPolicy: String,
    val reviews: List<Review>,
    val shippingInformation: String,
    val sku: String,
    val stock: Int,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val warrantyInformation: String,
    val weight: Int
):Serializable

object Converters {
    @TypeConverter
    fun fromStringToMeta(value: String?): Meta {
        return Gson().fromJson(value, Meta::class.java)
    }

    @TypeConverter
    fun fromMetaToString(list: Meta): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToDimensions(value: String?): Dimensions {
        return Gson().fromJson(value, Dimensions::class.java)
    }

    @TypeConverter
    fun fromDimensionsToString(list: Dimensions): String {
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun toArrayList(json: String?): List<String?>? {
        val gson = Gson()
        return json?.let {
            val type = object : TypeToken<List<String?>>() {}.type
            gson.fromJson(it, type)
        }
    }

    @TypeConverter
    fun fromArrayList(list: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun toList(json: String?): List<Review?>? {
        val gson = Gson()
        return json?.let {
            val type = object : TypeToken<List<Review>>() {}.type
            gson.fromJson(it, type)
        }
    }

    @TypeConverter
    fun fromList(list: List<Review?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}