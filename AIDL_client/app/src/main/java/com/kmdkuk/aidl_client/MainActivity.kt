package com.kmdkuk.aidl_client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.kmdkuk.IMyService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var iMyService: IMyService? = null

    val mConnection = object : ServiceConnection {
        // Called when the connection with the service is established
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            iMyService = IMyService.Stub.asInterface(service)
            Toast.makeText(applicationContext, "Service Connected", Toast.LENGTH_SHORT).show()
        }

        // Called when the connection with the service disconnects unexpectedly
        override fun onServiceDisconnected(className: ComponentName) {
            Log.e("com.kmdkuk.aidl_client.MainActivity", "Service has unexpectedly disconnected")
            iMyService = null
            Toast.makeText(applicationContext, "Service Disconnected", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nextButton.setOnClickListener {
            val field_str = editText.text.toString()
            val lines = field_str.count { it == '\n' } + 1
            var count = -1
            val field = Array(lines) {count++;Array(lines) { index ->
                println(index + (lines+1)*count)
                val target = field_str[index + (lines+1)*count]
                if(target == '■'){
                    1
                }else if(target == '□') {
                    0
                }else{
                    -1
                }
            }
            }
            // TODO
            var result = ""
            field.forEach {
                it.forEach {
                    if (it == 1) {
                        result = result.plus('■')
                    } else if (it == 0) {
                        result = result.plus('□')
                    }
                }
                result = result.plus('\n')
            }
            editText.setText(result.toString().removeSuffix("\n"))
        }
    }

    override fun onStart() {
        super.onStart()
        if (iMyService == null) {
            val it = Intent("myservice")
            it.setPackage("com.kmdkuk.aidl")
            bindService(it, mConnection, Context.BIND_AUTO_CREATE)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unbindService(mConnection)
    }
}
