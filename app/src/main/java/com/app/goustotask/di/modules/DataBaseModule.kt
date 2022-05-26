package com.app.goustotask.di.modules

import android.content.Context
import com.app.goustotask.data.local.AppDatabase
import com.app.goustotask.data.local.dao.ProductDao
import com.app.goustotask.data.local.repository.AbstractLocalRepository
import com.app.goustotask.data.local.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun providesLocalRepository(productDao: ProductDao): AbstractLocalRepository {
        return LocalRepository(productDao)
    }

    @Singleton
    @Provides
    fun provideProductsDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productsDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

}