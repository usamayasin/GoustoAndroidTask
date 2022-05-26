package com.app.goustotask.data.local.repository

import com.app.goustotask.data.local.dao.ProductDao
import com.app.goustotask.data.local.models.ProductsEntity
import javax.inject.Inject

class LocalRepository @Inject constructor(var productsDao: ProductDao) : AbstractLocalRepository() {

   override fun insertProducts(productEntities: List<ProductsEntity>) {
        productsDao.insertProducts(productEntities)
    }

   override fun getAllProducts(): List<ProductsEntity> {
        return productsDao.getAllProducts()
    }
}