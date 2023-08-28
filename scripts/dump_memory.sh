#!/bin/sh

echo "[INFO] Starting dump memory"
echo pmemsave 0x0000 2147483648 \"/tmp/test.bin\" | nc -N 127.0.0.1 5555
