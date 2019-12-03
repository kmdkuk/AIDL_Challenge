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

        override fun new_generation(target: Int, up: Int, down: Int, left: Int, right: Int): Int {
            val surround = up + down + left + right
            // 誕生
            if(target == 0 && surround == 3) return 1
            // 維持
            if(target == 1 && (surround == 2 || surround == 3)) return 1
            // 死亡
            return 0
        }

        override fun new_life(target: IntArray?): Int {
            val surround = target!!.sum() - target[4]
            // 誕生
            if(target[4] == 0 && surround == 3) return 1
            // 維持
            if(target[4] == 1 && (surround == 2 || surround == 3)) return 1
            // 過疎
            if(target[4] == 1 && surround <= 1) return 0
            // 過密
            if(target[4] == 1 && surround >= 4) return 0
            return 0

        }

    }
}