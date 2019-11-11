package com.example.button_press

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.util.logging.StreamHandler

class ButtonPressPlugin: MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "button_press")
      channel.setMethodCallHandler(ButtonPressPlugin())
      val eventChannel = EventChannel(registrar.messenger(), "onHomeButtonPress")

        eventChannel.setStreamHandler(
                object: EventChannel.StreamHandler {
                    override fun onListen(p0: Any?, p1: EventChannel.EventSink) {
                        val mHomeWatcher = HomeWatcher(registrar.context())
                        mHomeWatcher.setOnHomePressedListener(object : OnHomePressedListener {
                            override fun onHomePressed() {
                               p1.success(true)
                            }

                            override fun onHomeLongPressed() {}
                        })
                        mHomeWatcher.startWatch()
                    }
                    override fun onCancel(p0: Any) {
                    }
                }
        )


    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }
}
