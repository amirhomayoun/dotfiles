 # set the cursor
 xsetroot -cursor_name left_ptr
 # set German keyboard layout
 # setxkbmap -layout de
 #/usr/bin/xscreensaver -no-splash &
 # Start pulseaudio
 pulseaudio --start&
 # Start thunar as a daemon
 thunar --daemon &
 #Start mpd and mpdas
 mpd &
 pidof mpdas >& /dev/null
 if [ $? -ne 0 ]; then
  mpdas &
 fi
 
 xset s off -dpms &
 # start xmonad
 exec /home/homayoun/.xmonad/xmonad.start
