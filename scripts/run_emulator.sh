#!/bin/sh
# shellcheck disable=SC2164

adb shell getprop service.adb.tcp.port
cd "$HOME"/Staging-Area/android_sdk/emulator
./emulator -avd "$1" -no-window -no-snapshot \
            -qemu -cpu host -monitor telnet:127.0.0.1:5555,server,nowait
