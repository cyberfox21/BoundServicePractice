package com.cyberfox21.serviceapp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(LOG_TAG, "onBind")
        return Binder()
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(LOG_TAG, "onRebind")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(LOG_TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
    }

    private companion object {
        const val LOG_TAG = "CHECKER"
    }

}