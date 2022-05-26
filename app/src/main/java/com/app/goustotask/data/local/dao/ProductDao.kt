package com.app.goustotask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.goustotask.data.local.models.ProductsEntity
import com.app.goustotask.utils.Constants.TABLE_PRODUCTS

@Dao
abstract class ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProducts(productsEntity: List<ProductsEntity>)

    @Query("Select *FROM $TABLE_PRODUCTS")
    abstract fun getAllProducts(): List<ProductsEntity>

}