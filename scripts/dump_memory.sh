#!/bin/sh

# shellcheck disable=SC2006
while [ "`adb shell getprop sys.boot_completed | tr -d '\r' `" != "1" ]; do sleep 4; done
echo "Boot completed"
echo "Starting dump memory"
echo pmemsave 0x000 4096 \"/tmp/dest.mem\" | nc -N 127.0.0.1 5555
echo stop | nc -N 127.0.0.1 5555
echo quit | nc -N 127.0.0.1 5555