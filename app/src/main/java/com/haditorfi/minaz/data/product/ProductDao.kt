package com.haditorfi.minaz.data.product

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAll(): LiveData<List<Product>>

    @Insert
    suspend fun insert(vararg product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Update
    suspend fun update(product: Product)
}