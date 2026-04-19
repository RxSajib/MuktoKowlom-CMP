package com.aliad.muktokowlom.app

import android.app.Application
import android.content.Context
import com.aliad.muktokowlom.di.commonModule
import com.aliad.muktokowlom.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {

    companion object{
        lateinit var appContext: Context
    }


    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(context = this)

        initKoin {
         androidContext(this@MyApplication)
        }
    }

    fun initKoinAndroid(context: Context) {
        appContext = context
    }
}