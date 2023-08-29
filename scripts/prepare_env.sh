#!/bin/sh

# -------------------------------------------
echo "[INFO] Launching virtual event to malware"
adb shell monkey -p "$1" -v 4000

# remove temp file
rm "../temp.txt"