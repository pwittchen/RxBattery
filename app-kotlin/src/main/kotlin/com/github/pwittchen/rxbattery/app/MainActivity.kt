package com.github.pwittchen.rxbattery.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.pwittchen.rxbattery.library.RxBattery
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.textView

class MainActivity : AppCompatActivity() {

  private var batteryDisposable: Disposable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onResume() {
    super.onResume()
    batteryDisposable = RxBattery.observe(this)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        textView.text = String.format(
          "info: %s, status: %s, plugged: %s, health: %s",
          it.toString(), it.status(), it.plugged(), it.health()
        )
      }
  }

  override fun onPause() {
    super.onPause()
    if (batteryDisposable != null && batteryDisposable?.isDisposed!!) {
      batteryDisposable?.dispose()
    }
  }
}
