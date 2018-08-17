RxBattery
=========
🔋 Android library monitoring battery state of the device with RxJava and RxKotlin

Contents
--------

- [Usage](#usage)
- [Examples](#examples)
- [Download](#download)
- [Tests](#tests)
- [Code style](#code-style)
- [Static code analysis](#static-code-analysis)
- [Changelog](#changelog)
- [Releasing](#releasing)
- [References](#references)
- [License](#license)

Usage
-----

In the **Kotlin** application, you can use library as follows:

```kotlin
RxBattery
  .observe(context)
  .subscribeOn(Schedulers.io())
  .observeOn(AndroidSchedulers.mainThread())
  .subscribe { textView.text = it.toString() }
  }
```

In the **Java** application, you can use library as follows:

```java
RxBatteryFactory
  .observe(context)
  .subscribeOn(Schedulers.io())
  .observeOn(AndroidSchedulers.mainThread())
  .subscribe(batteryState -> {
    textView.setText(batteryState.toString());
  });
```

`BatteryState` looks as follows:

```kotlin
data class BatteryState(
  val status: Int,
  val plugged: Int,
  val health: Int
  val level: Int,
  val temperature: Int,
  val voltage: Int,
)
```

All `Integer` values returned by `BatteryState` object are reflected in constants of [`BatteryManager`](https://developer.android.com/reference/android/os/BatteryManager) class from the Android SDK.
For `status` search constants with `BATTERY_STATUS_` prefix, for `plugged` - `BATTERY_PLUGGED_`, for `health` - `BATTERY_HEALTH_`, `level` represents values from 0 to 100%, `voltage` and `temperature` are quite descriptive names.

Examples
--------

Exemplary Kotlin application is located in `app-kotlin` directory.

Exemplary Java application is located in `app-java` directory of this repository.

Download
--------

TBD.

Tests
-----

Tests are available in `library/src/test/kotlin/` directory and can be executed on JVM without any emulator or Android device from Android Studio or CLI with the following command:

```
./gradlew test
```

To generate test coverage report, run the following command:

```
./gradlew test jacocoTestReport
```

Code style
----------

Code style used in the project is called `SquareAndroid` from Java Code Styles repository by Square available at: https://github.com/square/java-code-styles.

Static code analysis
--------------------

Static code analysis runs Checkstyle, FindBugs, PMD, Lint, KtLint and Detekt. It can be executed with command:

```
./gradlew check
```

Reports from analysis are generated in library/build/reports/ directory.

Changelog
---------

See [CHANGELOG.md](https://github.com/pwittchen/RxBattery/blob/master/CHANGELOG.md) file.

Releasing
---------

See [RELEASING.md](https://github.com/pwittchen/RxBattery/blob/master/RELEASING.md) file.

References
----------
- https://developer.android.com/training/monitoring-device-state/index.html
- https://developer.android.com/training/monitoring-device-state/battery-monitoring.html
- https://developer.android.com/reference/android/os/BatteryManager
- https://developer.android.com/studio/profile/battery-historian.html
- https://stackoverflow.com/questions/3291655/get-battery-level-and-state-in-android
- https://stackoverflow.com/questions/25932677/proper-optimized-way-to-monitor-battery-level-in-android
- https://stackoverflow.com/questions/32608505/broadcast-receiver-monitoring-the-battery-level-and-charging-state
- https://github.com/jaredsburrows/android-gradle-kotlin-app-template

License
-------

    Copyright 2017 Piotr Wittchen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
