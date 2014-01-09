#set-option -g default-command bash
#unbind-key -a
unbind C-b
set -g prefix C-a

set -g base-index 1
set -g pane-base-index 1
set -g renumber-windows on

set -sg escape-time 1

#start tmux with my current environment
set-option -ga update-environment ' DISPLAY'

setw -g mode-keys vi
bind-key -t vi-copy 'v' begin-selection     # Begin selection in copy mode.
bind-key -t vi-copy 'C-v' rectangle-toggle  # Begin selection in copy mode.
bind-key -t vi-copy 'y' copy-selection      # Yank selection in copy mode.

#bind -t vi-copy v begin-selection
#bind -t vi-copy y copy-selection
bind -t vi-copy Escape cancel

setw -g monitor-activity on
set -g visual-activity on  

setw -g automatic-rename off


#set -g default-terminal "xterm-256color"
set -g default-terminal "rxvt-256color"

unbind %
bind H split-window -v
unbind '"'
bind V split-window -h

bind Left select-pane -L
bind Right select-pane -R
bind Up select-pane -U
bind Down select-pane -D

# vim movement bindings
set-window-option -g mode-keys vi
bind h select-pane -L
bind j select-pane -D
bind k select-pane -U
#bind l select-pane -R
#bind l last-window

bind-key -n M-NPage  swap-window -t -1
bind-key -n M-PPage swap-window -t +1

# pane movement
bind-key J command-prompt -p "join pane from:"  "join-pane -s '%%'"
bind-key S command-prompt -p "send pane to:"  "join-pane -t '%%'"

bind-key z resize-pane -Z \; display-message "Zoom zoom zoom" 

set -g history-limit 50000

set -g bell-action current
set -g bell-on-alert on

set -g display-panes-time 2000

set -g set-titles on
set -g set-titles-string "#{host} / #{session_name} : #I [ #W ]"

set -g pane-border-fg colour178
set -g pane-border-bg colour178
set -g pane-active-border-fg blue
set -g pane-active-border-bg blue

set-window-option -g status-bg black
set-window-option -g status-fg colour178

set-window-option -g window-status-fg default
set-window-option -g window-status-bg default
set-window-option -g window-status-attr dim

set-window-option -g window-status-current-fg default
set-window-option -g window-status-current-bg default
set-window-option -g window-status-current-attr "reverse"

set -g status-interval 1

#Bell colors
setw -g window-status-bell-attr    reverse,bold
setw -g window-status-bell-bg      red
setw -g window-status-bell-fg      green

#screen compatibility
bind-key A command-prompt "rename-window '%%'"
bind-key -r C-c new-window
bind-key -r C-d detach-client

# default statusbar colors
set-window-option -g status-left "{#{session_name}} [#{host}]"
set-window-option -g status-left-length 50
set-window-option -g status-left-fg colour14
set-window-option -g status-left-bg black

# default window title colors
set-window-option -g window-status-fg yellow #colour244 #base0
set-window-option -g window-status-bg default
set-window-option -g window-status-attr dim

set-window-option -g status-right "#T [%H:%M:%S] [%m/%d/%y]"
set-window-option -g status-right-length 50
set-window-option -g status-right-fg colour14
set-window-option -g status-right-bg black

#active window title colors
#set-window-option -g window-status-current-fg colour166 #orange
set-window-option -g window-status-current-fg green #orange
set-window-option -g window-status-current-bg default
set-window-option -g window-status-current-attr bright

#set -g status-left ' #[fg=cyan,bright]#10H#[fg=green]:#[fg=white]#S#[fg=green] | #[default]'
#set -g status-right '| #[fg=yellow]%y-%m-%d %H:%M '
set -g status-justify left