#!/bin/sh
# shellcheck disable=SC2164

# Check for adb is starting or not
adb shell getprop service.adb.tcp.port

# Cd to emulator path
cd "$HOME"/Staging-Area/android_sdk/emulator

# Run emulator
./emulator -avd "$1" -no-window -no-snapshot -memory 2048 -noaudio \
            -qemu -cpu host -monitor telnet:127.0.0.1:5555,server,nowait \
            -no-audio
