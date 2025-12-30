package com.aliad.log

import android.os.Build
import android.util.Log

actual fun appLogger(tag: String, message: String) {
    Log.d(tag, "appLogger: $message")
}