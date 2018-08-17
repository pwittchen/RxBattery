package com.github.pwittchen.rxbattery.library

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.FlowableEmitter
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock

class RxBatteryTest {

  @Test fun shouldCreateBroadcastReceiver() {
    // given
    val emitter = mock(FlowableEmitter::class.java)

    // when
    val broadcastReceiver: BroadcastReceiver = RxBattery.createBroadcastReceiver(
      emitter as FlowableEmitter<BatteryState>
    )

    // then
    assertThat(broadcastReceiver).isNotNull()
  }

  @Test fun shouldCallOnNextAfterReceivingData() {
    // given
    val emitter = mock(FlowableEmitter::class.java)
    val context: Context = mock(Context::class.java)
    val intent: Intent = mock(Intent::class.java)

    val broadcastReceiver: BroadcastReceiver = RxBattery.createBroadcastReceiver(
      emitter as FlowableEmitter<BatteryState>
    )

    // when
    broadcastReceiver.onReceive(context, intent)

    // then
    verify(emitter).onNext(any(BatteryState::class.java))
  }
}