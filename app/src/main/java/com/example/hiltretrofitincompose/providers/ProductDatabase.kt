package com.example.hiltretrofitincompose.providers

import android.content.Context
import androidx.room.Room
import com.example.hiltretrofitincompose.room.ProductDao
import com.example.hiltretrofitincompose.room.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductDatabase {

    @Singleton
    @Provides
    fun productDatabase(@ApplicationContext context:Context):ProductDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java,
            "product_database"
        ).build()
    }

    @Singleton
    @Provides
    fun productDao(productDatabase: ProductDatabase):ProductDao{
        return productDatabase
            .getProductDao()
    }

}