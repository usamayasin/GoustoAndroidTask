package com.app.goustotask

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null
        fun getInstance(): MyApplication {
            synchronized(MyApplication::class.java) {
                if (instance == null)
                    instance =
                        MyApplication()

            }
            return instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

}
