#!/bin/bash
toggle=$((pactl list sinks | grep -q Mute:.no && echo 1) || echo 0)
pactl set-sink-mute @DEFAULT_SINK@ $toggle
