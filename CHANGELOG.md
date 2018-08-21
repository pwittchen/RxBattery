CHANGELOG
=========

v. 0.1.0
--------
*?*

- **additions**
  - added the following enums: `Status`, `Plugged`, `Health`
  - added the following methods to `BatteryState` data class:
    - `fun status(): Status { ... }`
    - `fun plugged(): Plugged { ... }`
    - `fun health(): Health { ... }`
  - added `@JvmStatic` annotation to `fun observe(context: Context): Flowable<BatteryState>` method in `RxBattery` class in order to call static method from Java modules without need to explicitly call `Companion` object
  - added project logo created by @Yasujizr
- **updates**
  - fixed typo in const val in `RxBattery` class
  - **API-breaking change**: In `BatteryState` data class, renamed variable `status` to `statusCode`, `plugged` to `pluggedCode` and `health` to `healthCode`
- **removals**
  - **API-breaking change**: Removed `RxBatteryFactory` class

v. 0.0.1
--------
*17 Aug 2018*

The first release of the library.
