#!/bin/sh

# shellcheck disable=SC2164
cd "$HOME"/Staging-Area/android_sdk/emulator
./emulator -avd "Android-11-hehe" -no-window -no-snapshot -qemu -cpu host -monitor stdio &

# shellcheck disable=SC2006
while [ "`adb shell getprop sys.boot_completed | tr -d '\r' `" != "1" ]; do sleep 4; done
clear
printf "\n"
echo "Boot successfully"