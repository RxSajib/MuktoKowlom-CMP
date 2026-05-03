package com.aliad.utils

import dev.shivathapaa.logger.api.Log

object MyCustomLogger {

    fun logInfo(tag : String, message : String){
        Log.i(tag = tag, message = message)
    }
}