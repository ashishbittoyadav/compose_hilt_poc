package com.example.hiltretrofitincompose.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.hiltretrofitincompose.model.Converters
import com.example.hiltretrofitincompose.model.Product

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun getProductDao():ProductDao

//    companion object {
//        @Volatile
//        private var INSTANCE: ProductDatabase? = null
//
//        fun getDatabase(context: Context): ProductDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ProductDatabase::class.java,
//                    "product_database"
//                ).build()
//
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}