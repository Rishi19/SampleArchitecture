package com.architecture.domain

import android.app.Application
import android.content.IntentFilter
import android.os.Build
import com.architecture.ui.receiver.MyReceiver

class MyApplication : Application() {

    companion object{
        lateinit var component: MyComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerMyComponent.builder().appModule(AppModule(this)).build()

        val receiver = MyReceiver()
        val filter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            IntentFilter("android.net.wifi.WIFI_STATE_CHANGED")
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP_MR1")
        }
        registerReceiver(receiver, filter)
    }
}