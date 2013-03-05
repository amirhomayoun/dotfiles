#!/bin/sh

#mpc --format "%artist% #Â» %album% #Â» %track% #Â» %title% #Â» %time%" |grep Â»
#mpc --format " %title%" |head -n 1
mpc --format "%artist%, %title%"|grep -a1 playing|head -n 1

