package com.github.pwittchen.rxbattery.library

data class BatteryState(
  val status: Int,
  val plugged: Int,
  val level: Int,
  val temperature: Int,
  val voltage: Int,
  val health: Int
)
//TODO #2: write method to transform integers to proper object