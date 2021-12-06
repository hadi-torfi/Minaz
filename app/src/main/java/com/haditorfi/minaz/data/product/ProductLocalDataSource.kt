package com.haditorfi.minaz.data.product

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductLocalDataSource : ProductDataSource {

    @Query("SELECT * FROM product")
    override fun getAll(): LiveData<List<Product>>

    @Insert
    override suspend fun insert(vararg product: Product)

    @Delete
    override suspend fun delete(product: Product)

    @Update
    override suspend fun update(product: Product)
}