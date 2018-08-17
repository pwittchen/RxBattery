package com.github.pwittchen.rxbattery.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.github.pwittchen.rxbattery.library.RxBattery
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

  private var batteryDisposable: Disposable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onResume() {
    super.onResume()
    val textView: TextView = findViewById(R.id.textView)
    batteryDisposable = RxBattery.observe(this)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { textView.text = it.toString() }
  }

  override fun onPause() {
    super.onPause()
    if (batteryDisposable != null && batteryDisposable?.isDisposed!!) {
      batteryDisposable?.dispose()
    }
  }
}
