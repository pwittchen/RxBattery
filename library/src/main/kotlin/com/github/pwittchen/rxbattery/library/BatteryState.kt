package com.github.pwittchen.rxbattery.library

data class BatteryState(val batteryStatus: Int, val batteryPlugged: Int)
//TODO #1: enhance this class with more values: see https://stackoverflow.com/questions/32608505/broadcast-receiver-monitoring-the-battery-level-and-charging-state
//TODO #2: write method to transform integers to proper object