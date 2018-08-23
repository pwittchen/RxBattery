/*
 * Copyright (C) 2018 Piotr Wittchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pwittchen.rxbattery.library

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.common.truth.Truth.assertThat
import io.reactivex.FlowableEmitter
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

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