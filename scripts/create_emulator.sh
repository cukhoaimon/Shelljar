#!/bin/bash

# shellcheck disable=SC2164
cd "$HOME"/Staging-Area/android_sdk/cmdline-tools/latest/bin/
yes "" | ./avdmanager create avd \
                    -n "Android-13" \
                    -k "system-images;android-33;google_apis;x86_64"
