#!/bin/sh

sleep "$1";

echo "[INFO] Starting dump memory"
echo pmemsave 0x0000 2147483648 \""$PWD"/"$2"\" | nc -N 127.0.0.1 5555
echo "[INFO] Dump memory successful"