import XMonad
import XMonad.Layout.ResizableTile -- (5)  resize non-master windows too
import XMonad.Layout.PerWorkspace  
import XMonad.Hooks.DynamicLog
import XMonad.Hooks.ManageDocks
import XMonad.Util.Run(spawnPipe)
import XMonad.Util.EZConfig(additionalKeys)
import System.IO
import XMonad.Actions.SpawnOn
import XMonad.Actions.CycleWS
import XMonad.Actions.GridSelect
import XMonad.Hooks.EwmhDesktops
import XMonad.Prompt
import XMonad.Prompt.Window
import XMonad.Hooks.UrgencyHook
import XMonad.Layout.NoBorders
 
myManageHook = composeAll
    [ className =? "Gimp"      --> doFloat
    , className =? "evince" --> doShift "7"
    , className =? "Vncviewer" --> doFloat
    ]
--myStartUpHook =
--      spawnOn "workspace1" "program1"
--      â€¦
--      spawnOn "workspaceN" "programN"


--myWorkspaces = ["1","2","3","4","5-Wb","6","7-Ch","8-Ml","9-Lg"]  
myWorkspaces = ["1☀","2☻","3✖","4◑","5❀","6☂","7♫","8☎","9◕"]  
myNormalBorderColor = "#ff0000"
myFocusedBorderColor = "#00FF00"
myTerminal = "urxvt"
 
main = do
    xmproc <- spawnPipe "/usr/bin/xmobar -x 0 /home/homayoun/.xmonad/.xmobarrc"
    xmproc2 <- spawnPipe "/usr/bin/xmobar -x 1 /home/homayoun/.xmonad/.xmobarrc2"
--    xmproc3 <- spawnPipe "killall trayer"
--    xmproc4 <- spawnPipe "/home/homayoun/.xmonad/trayer.sh"
    xmproci5 <- spawnPipe "killall everpad; everpad"
    xmproci6 <- spawnPipe "killall clipit; clipit"
    xmproci7 <- spawnPipe "guake"
    xmonad $  ewmh $ withUrgencyHook NoUrgencyHook $ defaultConfig
        { manageHook = manageDocks <+> myManageHook -- make sure to include myManageHook definition from above
                        <+> manageHook defaultConfig
        , layoutHook = avoidStruts  $  layoutHook defaultConfig
--        ,  startupHook = do
--          spawnOn  "8" "evince"
--          spawnOn "test" "evince"
--      , startupHook = myStartUpHook
        , logHook = dynamicLogWithPP xmobarPP
                        { ppOutput = hPutStrLn xmproc
                        , ppTitle = xmobarColor "green" "" . shorten 50
			, ppUrgent = xmobarColor "red" ""  . shorten 30
                        }
		  >> dynamicLogWithPP xmobarPP
                        { ppOutput = hPutStrLn xmproc2
                        , ppTitle = xmobarColor "green" "" . shorten 50
			, ppUrgent = xmobarColor "red" ""  . shorten 50
                        }

        , modMask = mod4Mask     -- Rebind Mod to the Windows key
	, workspaces = myWorkspaces
	, terminal = myTerminal
	, focusedBorderColor = myFocusedBorderColor
	, normalBorderColor = myNormalBorderColor

	} `additionalKeys`
        [ ((mod4Mask .|. shiftMask, xK_z), spawn "xscreensaver-command -lock")
        , ((mod4Mask .|. shiftMask, xK_F4), spawn "xscreensaver-command -lock; sleep 1; xset dpms force standby")
        , ((controlMask, xK_Print), spawn "sleep 0.2; scrot -s")
        , ((0, xK_Print), spawn "scrot")
--        , ((mod4Mask.|. shiftMask,xK_z),    spawn "gnome-screensaver-command -l")
        , ((mod4Mask,               xK_a), sendMessage MirrorShrink)
        , ((mod4Mask,               xK_z), sendMessage MirrorExpand)
        
--        , (( mod4Mask, xK_c), spawn "google-chrome") -- to open Google chrome 
--        , (( mod4Mask .|. shiftMask, xK_F4), spawn "sudo shutdown -h now") -- window key + Shift + F4 to shutdown system  
        , (( mod4Mask, xK_F4), kill) -- to kill applications  
        , ((0  , 0x1008FF11), spawn "amixer -q set Master 5%- unmute") --Control volume 
        , ((0  , 0x1008FF13), spawn "amixer -q set Master 5%+ unmute") --Control volume 
--        , ((0  , 0x1008FF12), spawn "amixer -q set Master toggle") --Control volume 
        , ((0  , 0x1008FF12), spawn "~/.xmonad/ToggleMute.sh") --Control volume 
--        , ((0  , 0x1008FF12), spawn "pactl set-sink-mute @DEFAULT_SINK@ 1") --Control volume 
--
--        , ((0  , 0x1008FF14), spawn " mocp --toggle-pause") --
--        , ((0  , 0x1008FF15), spawn " mocp --stop") --
--        , ((0  , 0x1008FF16), spawn " mocp --previous") --
--        , ((0  , 0x1008FF17), spawn " mocp --next") --
        , ((0  , 0x1008FF14), spawn "ncmpcpp toggle;notify-send play/pause")
        , ((0  , 0x1008FF18), spawn "ncmpcpp stop; notify-send stop")
        , ((0  , 0x1008FF16), spawn "ncmpcpp prev;notify-send Previous ")
        , ((0  , 0x1008FF17), spawn "ncmpcpp next")
        , ((0, xK_Print), spawn "scrot") -- use the print key to capture screenshot with scrot  
--      , ((mod4Mask,               xK_Down),  nextWS)
--	, ((mod4Mask,               xK_Up),    prevWS)
--	, ((mod4Mask .|. shiftMask, xK_Down),  shiftToNext)
--	, ((mod4Mask .|. shiftMask, xK_Up),    shiftToPrev)
--	, ((mod4Mask,               xK_Right), nextScreen)
--	, ((mod4Mask,               xK_Left),  prevScreen)
	, ((mod4Mask .|. shiftMask, xK_Right), shiftNextScreen)
	, ((mod4Mask .|. shiftMask, xK_Left), shiftPrevScreen)
	, ((mod4Mask,               xK_z),     toggleWS)
        , ((mod4Mask     , xK_f), moveTo Next EmptyWS)  -- find a free workspace
        , ((mod4Mask .|. shiftMask, xK_f), shiftTo Next EmptyWS)  -- shift to the next free workspace
--      , ((mod4Mask, xK_g), goToSelected defaultGSConfig)
--      , ((mod4Mask, xK_s), spawnSelected defaultGSConfig ["xterm","gmplayer","gvim"])
        , ((mod4Mask, xK_a), spawn "/home/homayoun/.xmonad/ChooseWindow.sh" ) -- use mod4Mask + a to see all the windows in dmenu  
        , ((mod4Mask, xK_r), spawn "/home/homayoun/.xmonad/FindFiles.sh") -- use mod4Mask + r to see a list of all files of Home directory in dmenu 
        , ((mod4Mask .|. shiftMask, xK_r), spawn "/home/homayoun/.xmonad/FindDirectories.sh") -- use mod4Mask + shift + r to see a list of all directories in the Home directory in dmenu 
        , ((mod4Mask, xK_g     ), windowPromptGoto  defaultXPConfig)
        , ((mod4Mask .|. shiftMask, xK_b     ), windowPromptBring defaultXPConfig)
	, ((mod4Mask, xK_b     ), sendMessage ToggleStruts)
        , ((mod4Mask              , xK_y), focusUrgent )
        , ((mod4Mask, xK_b     ), sendMessage ToggleStruts)
         -- Increment the number of windows in the master area
        , ((mod4Mask .|. shiftMask , xK_comma ), sendMessage (IncMasterN 1))
         -- Deincrement the number of windows in the master area
        , ((mod4Mask .|. shiftMask , xK_period), sendMessage (IncMasterN (-1)))
	-- start a pomodoro
	, ((mod4Mask              , xK_n     ), spawn "touch ~/.pomodoro_session")
	-- Run google calendar
	, ((mod4Mask,xK_c), spawn "chromium-browser --app='https://www.google.com/calendar/b/1/render'")
--        , ((mod4Mask .|. controlMask, xK_Right),        -- a crazy keybinding!
--         do t <- findWorkspace getSortByXineramaRule Next NonEmptyWS 2
--            windows . view $ t                                         )
--      ,((mod4Mask, xk_y), spawn "home/user/scripts/somescript.sh" ) -- use mod4Mask + y to run a script  
        

    -- volume control.
--        , ("M-xK_F6", raiseVolume 4 >> return ())
--        , ((mod4Mask,xK_F6), spawn "amixer -q set Master 5%- unmute") 
--        , ((mod4Mask,xK_F7), spawn "amixer -q set Master 5%+ unmute") 
--        , ("<XF86AudioMute>", spawn "amixer -q set Master toggle")
--        , ("<XF86AudioLowerVolume>", spawn "amixer -q set Master 5%- unmute")
--        , ("<XF86AudioRaiseVolume>", spawn "amixer -q set Master 5%+ unmute")
        ]
--main = xmonad defaultConfig { keys = keys defaultConfig `mappend`
--    \c -> fromList [
--        ((0, xK_F6), lowerVolume 4 >>= alert),
--        ((0, xK_F7), raiseVolume 4 >>= alert)
--    ]
--}
