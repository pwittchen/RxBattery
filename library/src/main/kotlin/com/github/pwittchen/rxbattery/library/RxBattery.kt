package com.github.pwittchen.rxbattery.library

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import io.reactivex.BackpressureStrategy.BUFFER
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter

class RxBattery {
  companion object {
    const val UNKNOWN = -1

    fun observe(context: Context): Flowable<BatteryState> {

      var receiver: BroadcastReceiver? = null

      return Flowable.create<BatteryState>({ emitter ->
        receiver = createBroadcastReceiver(emitter)
        context.registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
      }, BUFFER)
        .doOnCancel { context.unregisterReceiver(receiver) }
    }

    fun createBroadcastReceiver(emitter: FlowableEmitter<BatteryState>): BroadcastReceiver {
      return object : BroadcastReceiver() {
        override fun onReceive(
          context: Context?,
          intent: Intent?
        ) {
          if (intent == null) {
            return
          }

          val status: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, UNKNOWN)
          val plugged: Int = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, UNKNOWN)
          val health: Int = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, UNKNOWN)
          val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, UNKNOWN)
          val temperature: Int = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, UNKNOWN)
          val voltage: Int = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, UNKNOWN)

          emitter.onNext(BatteryState(status, plugged, health, level, temperature, voltage))
        }
      }
    }
  }
}