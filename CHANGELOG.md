CHANGELOG
=========

v. 0.1.0
--------
*?*

- fixed typo in const val in `RxBattery` class
- added the following enums: `Status`, `Plugged`, `Health`
- **API-breaking change**: In `BatteryState` data class renamed variable `status` to `statusCode`, `plugged` to `pluggedCode` and `health` to `healthCode`
- **API-breaking change**: Removed `RxBatteryFactory` class
- added the following methods to `BatteryState` data class:
  - `fun status(): Status { ... }`
  - `fun plugged(): Plugged { ... }`
  - `fun health(): Health { ... }`
- Added `@JvmStatic` annotation to `fun observe(context: Context): Flowable<BatteryState>` method in `RxBattery` class in order to call static method from Java modules without need to explicitly call `Companion` object
- Added project logo created by @Yasujizr

v. 0.0.1
--------
*17 Aug 2018*

The first release of the library.
