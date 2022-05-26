package com.app.goustotask.data.local.repository

import com.app.goustotask.data.local.models.ProductsEntity

abstract class AbstractLocalRepository {
    abstract fun insertProducts(productEntities: List<ProductsEntity>)
    abstract fun getAllProducts():List<ProductsEntity>
}