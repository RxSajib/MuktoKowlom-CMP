package com.aliad.muktokowlom.app

import android.app.Application
import com.aliad.muktokowlom.di.commonModule
import com.aliad.muktokowlom.di.initKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
          this@MyApplication
        }
    }
}