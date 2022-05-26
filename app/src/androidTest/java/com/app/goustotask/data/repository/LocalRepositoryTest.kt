package com.app.goustotask.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.app.goustotask.data.local.AppDatabase
import com.app.goustotask.data.local.dao.ProductDao
import com.app.goustotask.data.local.models.ProductsEntity
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LocalRepositoryTest {

    private lateinit var productsDao: ProductDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun start() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        productsDao = appDatabase.productsDao()
    }

    @Test
    fun insertAndGetProductsTest() = runBlocking {
        val item = ProductsEntity(
            id = "0009468c-33e9-11e7-b485-02859a19531d",
            title = "Borsao Macabeo",
            description = "A flavoursome Summer wine made from the indigenous Macabeo grape in northern Spain. A balanced, modern white with flavours of ripe peach, zesty lemon and nutty undertones, it leaves the palate with a clean and fruity finish.",
            price = "6.95",
            isForSale = false,
            ageRestricted = true,
            volume = 1000,
            zone = "Ambient",
            imageUrl = "https://production-media.gousto.co.uk/cms/product-image-landscape/YAddOns-WhiteWines-Borsao_013244-x750.jpg",
            category = "Drinks Cabinet,Large Alcohol"
        )
        productsDao.insertProducts(listOf(item))
        val result = productsDao.getAllProducts()
        assertNotNull(result)
    }
}