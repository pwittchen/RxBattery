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

import android.os.BatteryManager
import com.github.pwittchen.rxbattery.library.state.Health
import com.github.pwittchen.rxbattery.library.state.Plugged
import com.github.pwittchen.rxbattery.library.state.Status
import com.github.pwittchen.rxbattery.library.state.Status.NOT_CHARGING

data class BatteryState(
  val statusCode: Int,
  val pluggedCode: Int,
  val healthCode: Int,
  val level: Int,
  val temperature: Int,
  val voltage: Int
) {

  fun status(): Status {
    return when (statusCode) {
      BatteryManager.BATTERY_STATUS_CHARGING -> Status.CHARGING
      BatteryManager.BATTERY_STATUS_DISCHARGING -> Status.DISCHARGING
      BatteryManager.BATTERY_STATUS_FULL -> Status.FULL
      BatteryManager.BATTERY_STATUS_NOT_CHARGING -> NOT_CHARGING
      else -> Status.UNKNOWN
    }
  }

  fun plugged(): Plugged {
    return when (pluggedCode) {
      BatteryManager.BATTERY_PLUGGED_AC -> Plugged.AC
      BatteryManager.BATTERY_PLUGGED_USB -> Plugged.USB
      BatteryManager.BATTERY_PLUGGED_WIRELESS -> Plugged.WIRELESS
      else -> Plugged.UNKNOWN
    }
  }

  fun health(): Health {
    return when (healthCode) {
      BatteryManager.BATTERY_HEALTH_COLD -> Health.COLD
      BatteryManager.BATTERY_HEALTH_DEAD -> Health.DEAD
      BatteryManager.BATTERY_HEALTH_GOOD -> Health.GOOD
      BatteryManager.BATTERY_HEALTH_OVERHEAT -> Health.OVERHEAT
      BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> Health.OVER_VOLTAGE
      BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> Health.UNSPECIFIED_FAILURE
      else -> Health.UNKNOWN
    }
  }
}