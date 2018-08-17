package com.github.pwittchen.rxbattery.library

import android.content.BroadcastReceiver
import com.google.common.truth.Truth.assertThat
import io.reactivex.FlowableEmitter
import org.junit.Test
import org.mockito.Mockito

class RxBatteryTest {

  @Test fun shouldCreateBroadcastReceiver() {

    val emitter = Mockito.mock(FlowableEmitter::class.java)

    val broadcastReceiver: BroadcastReceiver = RxBattery.createBroadcastReceiver(
      emitter as FlowableEmitter<BatteryState>
    )

    assertThat(broadcastReceiver).isNotNull()
  }
}