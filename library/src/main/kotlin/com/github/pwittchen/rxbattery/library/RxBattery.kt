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
    const val UNKNOWN_STATUS = -1

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

            val status: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, UNKNOWN_STATUS)
            val plugged: Int = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, UNKNOWN_STATUS)
            emitter.onNext(BatteryState(status, plugged))
          }
        }

        context.registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

      }, BUFFER)

      flowable.doOnCancel { context.unregisterReceiver(receiver) }
      return flowable
    }
  }
}