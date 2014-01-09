!
#ifdef COLOR
?*Background: lightSteelBlue
*Background: lightSteelBlue
Mwm*Background: default
#endif
xterm*loginShell:true

! The following sets the customization resource to get 
! special app-defaults, if they exist:

#ifdef COLOR
*Customization: -color
#else
*Customization: -mono
#endif


Mwm.keyboardFocusPolicy:	explicit
Mwm*XClock*clientDecoration:	Border
Mwm*XBiff*clientDecoration:	Border
Mwm*iconDecoration:		label activelabel
Mwm*iconPlacement:		bottom right
rxvtop1*background:lightgray
rxvtop1*foreground:Black
rxvtop*background:Black
rxvtop*foreground:Yellow
rxvtop2*background:Black
rxvtop2*foreground:Green




!-------------------------------------------------------------------------------
! Xft settings
!-------------------------------------------------------------------------------

Xft.dpi:                    96
!Xft.dpi:                    256
Xft.antialias:              true 
Xft.rgba:                   rgb
Xft.hinting:                true
Xft.hintstyle:              hintslight

!-------------------------------------------------------------------------------
! URxvt settings
! Colours lifted from Solarized (http://ethanschoonover.com/solarized)
! More info at:
! http://pod.tst.eu/http://cvs.schmorp.de/rxvt-unicode/doc/rxvt.1.pod
!-------------------------------------------------------------------------------
! Colors
URxvt*dynamicColors:        on
URxvt.intensityStyles:      false
!URxvt.termName:             xterm-256color
URxvt.termName:             rxvt-256color
URxvt.depth:                32
URxvt.geometry:             160x30
URxvt.transparent:          false
URxvt.fading:               0
URxvt.urgentOnBell:         true
URxvt.visualBell:           false 
URxvt.loginShell:           true
URxvt.saveLines:            12000
URxvt.internalBorder:       3
URxvt.lineSpace:            0

! Fonts

! bitmap fonts
!
URxvt.font: -misc-fixed-medium-r-normal-*-35-140-75-75-c-90-iso8859-1
!Rxvt.font: *-fixed-*-*-*-20-*\007
!URxvt.font: 9x15
!URxvt.font: 9x15bold


! Xft fonts
!
!URxvt*font:     *-fixed-*-*-*-14-*
!URxvt*font:     *-terminus-*-*-*-14-*
!URxvt.font: xft:Arial:bold:size=10



!URxvt.font: xft:DejaVu Sans Mono:pixelsize=12

!URxvt*boldFont: *-fixed-*-*-*-14-*
!URxvt.allow_bold:           false
/* URxvt.font:
   !    -*-terminus-medium-r-normal-*-12-120-72-72-c-60-iso8859-1 */
!Urxvt*font:xft:Bitstream Vera Sans Mono-8:antialias=true
!URxvt.font: xft:terminus:size=12
!Urxvt*font: xft:Arial Mono:pixelsize=12 
!URxvt*font: xft:DejaVu Sans Mono:pixelsize=12
!URxvt*font: xft:Liberation Mono:pixelsize=13:antialias=true:hinting=true
!URxvt*font: xft:Liberation Mono:pixelsize=13:antialias=true:hinting=true
!URxvt*boldFont: xft:Liberation Mono:bold:pixelsize=13:antialias=true:hinting=true
!URxvt*font: xft:Monospace:pixelsize=14
!URxvt*font: xft:Monospace:pixelsize=14
!URxvt*font: xft:Monospace:pixelsize=18:antialias=true:hinting=true
!URxvt*boldFont: xft:Monospace:pixelsize=18:antialias=true:hinting=true
!Liberation Mono:style=Regular
! Fix font space
!URxvt*letterSpace: -1

! :Bind C-0, C-+ and C-= to activate small, medium, and big font size resp.     
! URxvt.keysym.C-0: command:\033]710;*-fixed-*-*-*-16-*
! URxvt.keysym.C-minus: command:\033]710;*-fixed-*-*-*-14-*\007
! URxvt.keysym.C-equal: command:\033]710;*-fixed-*-*-*-18-*\007


! Resize terminal with C-7,8
 URxvt.keysym.C-7: command:\033[8;24;80t 
 URxvt.keysym.C-8: command:\033[8;50;80t 

! Resize fonts with C-1,2,3,4,5,6
! URxvt.keysym.C-1: command:\033]710;6x13\007
! URxvt.keysym.C-2: command:\033]710;8x13\007
 URxvt.keysym.C-1: command:\033]710;*-fixed-*-*-*-14-*\007
 URxvt.keysym.C-2: command:\033]710;*-fixed-*-*-*-16-*\007
 URxvt.keysym.C-3: command:\033]710;*-fixed-*-*-*-18-*\007
 URxvt.keysym.C-4: command:\033]710;*-fixed-*-*-*-20-*\007


!URxvt.perl-lib: $HOME/.urxvt/ext/

! Perl extensions
URxvt.perl-ext-common:      default,tabbed
URxvt.matcher.button:       1
URxvt.urlLauncher:          firefox


! URxvt.keysym.C-Up:     perl:font-size:increase
! URxvt.keysym.C-Down:   perl:font-size:decrease
! URxvt.keysym.C-S-Up:   perl:font-size:incglobal
! URxvt.keysym.C-S-Down: perl:font-size:decglobal

! Scrollbar
!URxvt.scrollStyle:          rxvt
URxvt.scrollstyle:          plain
URxvt.scrollBar:            true 
Urxvt.secondaryScroll:      true
URxvt.scrollBar_floating:   true
URxvt.scrollColor:          #252525
URxvt*scrollTtyOutput:      true
URxvt*scrollWithBuffer:     true
URxvt*scrollTtyKeypress:    true

!Buffer size
URxvt*saveLines:10000

! Tab
!URxvt.perl-ext-common:  default,tabbed

URxvt.tabbed.tabbar-fg: 2
URxvt.tabbed.tabbar-bg: 0
URxvt.tabbed.tab-fg:    3
URxvt.tabbed.tab-bg:    0
URxvt*foreground: 7
URxvt*background: 0

! switch tabs

URxvt.keysym.Control-Tab: \033[27;5;9~

URxvt.keysym.Control-Up:     \033[1;5A
URxvt.keysym.Control-Down:   \033[1;5B
!URxvt.keysym.Control-Left:   \033[1;5D
!URxvt.keysym.Control-Right:  \033[1;5C

! Bell

!URxvt.urgentOnBell: true

! Cursor
URxvt.cursorBlink:          true
URxvt.cursorColor:          #657b83
URxvt.cursorUnderline:      false

! Pointer
!URxvt.pointerBlank:         true

! Fix slow refresh rate
URxvt*buffered: false

! Home end keys

 URxvt*keysym.Home: \033[1~
 URxvt*keysym.End: \033[4~
!*ttyModes: erase ^?

! Anti-aliasing
!Xft.lcdfilter: lcddefault

! :Bind C-0, C-+ and C-= to activate small, medium, and big font size resp.     
!  URxvt.keysym.C-0: command:\033]710;-*-dina-medium-r-normal--13-*-*-*-*-*-*-*\007
!  URxvt.keysym.C-minus: command:\033]710;-*-dina-medium-r-normal--15-*-*-*-*-*-*-*\007
!  URxvt.keysym.C-equal: command:\033]710;-*-dina-medium-r-normal--16-*-*-*-*-*-*-*\007

!!!Source http://github.com/altercation/solarized
!
!!*background: #002b36
!*background: #191919
!*foreground: #657b83
!!!*fading: 40
!*fadeColor: #002b36
!*cursorColor: #93a1a1
!*pointerColorBackground: #586e75
!*pointerColorForeground: #93a1a1
!! 
!!! black dark/light
!*color0: #073642
!*color8: #002b36
! 
!!! red dark/light
!*color1: #dc322f
!*color9: #cb4b16
! 
!!! green dark/light
!*color2: #859900
!*color10: #586e75
! 
!!! yellow dark/light
!*color3: #b58900
!*color11: #657b83
! 
!! blue dark/light
!*color4: #268bd2
!*color12: #839496
! 
!!! magenta dark/light
!*color5: #d33682
!*color13: #6c71c4
! 
!!! cyan dark/light
!*color6: #2aa198
!*color14: #93a1a1
! 
!!! white dark/light
!*color7: #eee8d5
!*color15: #fdf6e3

! New SET
! CURRENT SET
!*foreground: #6F6767
!*background: #3A3636
! black
Rxvt.color0  : #000000
Rxvt.color8  : #555555
! red
Rxvt.color1  : #AA0000
Rxvt.color9  : #FF5555
! green
Rxvt.color2  : #00AA00
Rxvt.color10 : #55FF55
! yellow
Rxvt.color3  : #AA5500
Rxvt.color11 : #FFFF55
! blue
!URxvt.color4  : #0000AA
Rxvt.color4  : #5555FF
Rxvt.color12 : #5555FF
! magenta
Rxvt.color5  : #AA00AA
Rxvt.color13 : #FF55FF
! cyan
Rxvt.color6  : #00AAAA
Rxvt.color14 : #55FFFF
! white
Rxvt.color7  : #AAAAAA
Rxvt.color15 : #FFFFFF
!!
!! NEW SET
!!colors:
!!scheme: pnevma
!!*foreground: #696969
!!*background: #282626
!*foreground: #6F6767
!*background: #3A3636
!*color0: #363636
!*color8: #424242
!*color1: #C37561
!*color9: #D19485
!*color2: #A0A57E
!*color10: #B6B99D
!*color3: #D1A375
!*color11: #DEBC9C
!*color4: #7985A3
!*color12: #98A1B9
!*color5: #AB716D
!*color13: #BE918E
!*color6: #98B9B1
!*color14: #CBE6CB
!*color7: #D0D0D0
!*color15: #E6E6E6

! NEW SET
!
!URxvt.foreground: #CCCCCC
!URxvt.background: #4B5D7E
!
!! Normal
!! color0    black
!! color1    red
!! color2    green
!! color3    yellow
!! color4    blue
!! color5    purple
!! color6    cyan
!! color7    white
!
!urxvt.color0:    #6c6c6c
!urxvt.color1:    #e9897c
!urxvt.color2:    #b6e77d
!urxvt.color3:    #ecebbe
!urxvt.color4:    #a9cdeb
!urxvt.color5:    #ea96eb
!urxvt.color6:    #c9caec
!urxvt.color7:    #CCCCCC
!
!! Light
!! color8     black
!! color9     red
!! color10    green
!! color11    yellow
!! color12    blue
!! color13    purple
!! color14    cyan
!! color15    white
!
!urxvt.color8:    #747474
!urxvt.color9:    #f99286
!urxvt.color10:    #c3f786
!urxvt.color11:    #fcfbcc
!urxvt.color12:    #b6defb
!urxvt.color13:    #fba1fb
!urxvt.color14:    #d7d9fc
!
