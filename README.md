Welcome to _Safe Wallet_, a standalone Safe payment app for your Android device!

This project contains several sub-projects:

 * __wallet__:
     The Android app itself. This is probably what you're searching for.
 * __native-scrypt__:
     Native code implementation for Scrypt. The C files are copied from the
     Java Scrypt project at [GitHub](https://github.com/wg/scrypt)

You can build all sub-projects at once using Gradle:

`gradle clean build -x test`

Full Guide for building the APK:

`$ git clone https://github.com/BankLedger/android-safe-wallet-master`

`$ cd safe-wallet`

`$ gradle clean build -x test`


