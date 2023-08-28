#!/bin/sh

# shellcheck disable=SC2006
while [ "`adb shell getprop sys.boot_completed | tr -d '\r' `" != "1" ]; do sleep 4; done
echo "[INFO] Boot completed"
# -------------------------------------------
echo "[INFO]Starting install malware"
# adb install malware...

# -------------------------------------------
echo "[INFO] Launching virtual event to malware"
# adb shell monkey...

echo "Starting dump memory"
echo pmemsave 0x0000 2147483648 \"/tmp/test.bin\" | nc -N 127.0.0.1 5555

# Stop emulator
echo stop | nc -N 127.0.0.1 5555

# Quit qemu monitor
echo quit | nc -N 127.0.0.1 5555