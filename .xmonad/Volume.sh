#!/bin/sh

#bars=$(amixer get Master | awk -F'[]%[]' '/%/ {if ($7 == "off") { print "MM" } else { print $2/10 }}' | head -n 1), echo $bars

A=$(amixer -c 0 get Master | sed -n "$ p" | awk '{print $4,$6}')
echo $A
