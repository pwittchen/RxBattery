package com.github.pwittchen.rxbattery.library

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import io.reactivex.BackpressureStrategy.BUFFER
import io.reactivex.Flowable

class RxBattery {
  companion object {
    const val UKNOWN = -1

    fun observe(context: Context): Flowable<BatteryState> {

      var receiver: BroadcastReceiver? = null

      val flowable = Flowable.create<BatteryState>({ emitter ->
        receiver = object : BroadcastReceiver() {
          override fun onReceive(
            context: Context?,
            intent: Intent?
          ) {
            if (intent == null) {
              return
            }

            val status: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, UKNOWN)
            val plugged: Int = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, UKNOWN)
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, UKNOWN)
            val temperature: Int = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, UKNOWN)
            val voltage: Int = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, UKNOWN)
            val health: Int = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, UKNOWN)
            emitter.onNext(BatteryState(status, plugged, level, temperature, voltage, health))
          }
        }

        context.registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

      }, BUFFER)

      flowable.doOnCancel { context.unregisterReceiver(receiver) }
      return flowable
    }
  }

  @Suppress("RedundantCompanionReference")
  fun observe(context: Context): Flowable<BatteryState> {
    return Companion.observe(context)
  }
}