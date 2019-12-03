package com.kmdkuk.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.kmdkuk.IMyService

class MyService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    private val binder: IMyService.Stub = object : IMyService.Stub(){
        override fun Add(val1: Int, val2: Int): Int {
            return val1 + val2
        }
    }
}