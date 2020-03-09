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
package com.github.pwittchen.rxbattery.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
