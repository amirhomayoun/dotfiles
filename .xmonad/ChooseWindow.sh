#!/bin/sh

wmctrl -a "$(wmctrl -l | awk '{print $NF}' | dmenu -i)"
#wmctrl -a "$(wmctrl -l | awk '{print $2+1,":",$4}' | dmenu -i)"
#wmctrl -a "$(wmctrl -l | awk '{print $4}' | dmenu -i)"

#"wmctrl -l | awk '{print $4}' | dmenu"


