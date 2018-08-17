package com.github.pwittchen.rxbattery.library;

import android.content.Context;
import io.reactivex.Flowable;

public class RxBatteryFactory {

  private RxBatteryFactory() {
  }

  public static Flowable<BatteryState> observe(Context context) {
    return RxBattery.Companion.observe(context);
  }
}
