#!/bin/sh
# shellcheck disable=SC2164

adb shell getprop service.adb.tcp.port
cd "$HOME"/Staging-Area/android_sdk/emulator
./emulator -avd "$1" -no-window -no-snapshot -qemu -cpu host &
printf "\n"
# shellcheck disable=SC2006
while [ "`adb shell getprop sys.boot_completed | tr -d '\r' `" != "1" ]; do sleep 4; done
clear
echo "Boot successfully"