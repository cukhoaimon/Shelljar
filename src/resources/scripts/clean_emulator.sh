#!/bin/sh
# shellcheck disable=SC2164

# Stop emulator
echo stop | nc -N 127.0.0.1 5555

# Quit qemu monitor
echo quit | nc -N 127.0.0.1 5555

# Delete AVD
cd "$HOME"/Staging-Area/android_sdk/cmdline-tools/latest/bin/
 ./avdmanager delete avd -n "Android-13"

echo "[INFO] Done cleaning emulator"