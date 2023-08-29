#!/bin/sh

# shellcheck disable=SC2006
while [ "`adb shell getprop sys.boot_completed | tr -d '\r' `" != "1" ]; do sleep 4; done
echo "[INFO] Boot completed"

# install malware
echo "[INFO] Starting install malware"
adb install "$1"

# Echo to file
adb shell pm list packages -3 > PKGS