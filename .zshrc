# Path to your oh-my-zsh configuration.
ZSH=$HOME/.oh-my-zsh

# Set name of the theme to load.
# Look in ~/.oh-my-zsh/themes/
# Optionally, if you set this to "random", it'll load a random theme each
# time that oh-my-zsh is loaded.
ZSH_THEME="robbyrussell"

# Example aliases
# alias zshconfig="mate ~/.zshrc"
# alias ohmyzsh="mate ~/.oh-my-zsh"

# Set to this to use case-sensitive completion
# CASE_SENSITIVE="true"

# Comment this out to disable bi-weekly auto-update checks
# DISABLE_AUTO_UPDATE="true"

# Uncomment to change how many often would you like to wait before auto-updates occur? (in days)
# export UPDATE_ZSH_DAYS=13

# Uncomment following line if you want to disable colors in ls
# DISABLE_LS_COLORS="true"

# Uncomment following line if you want to disable autosetting terminal title.
# DISABLE_AUTO_TITLE="true"

# Uncomment following line if you want red dots to be displayed while waiting for completion
# COMPLETION_WAITING_DOTS="true"

# Which plugins would you like to load? (plugins can be found in ~/.oh-my-zsh/plugins/*)
# Custom plugins may be added to ~/.oh-my-zsh/custom/plugins/
# Example format: plugins=(rails git textmate ruby lighthouse)
plugins=(git github)

source $ZSH/oh-my-zsh.sh
#source /etc/zsh_command_not_found
[ -r /usr/share/doc/pkgfile/command-not-found.zsh ] && . /usr/share/doc/pkgfile/command-not-found.zsh

# Customize to your needs...
export PATH=/usr/lib/lightdm/lightdm:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games

qq_set_term_title(){
   echo -en "\033]0;$1\a"
   }
precmd () {print -Pn "\e]0; \a"}
alias Done="notify-send Done!;echo -e '\a'"
alias alert_helper='history|tail -n1|sed -e "s/^\s*[0-9]\+\s*//" -e "s/;\s*alert$//"'
alias alert='notify-send -i /usr/share/icons/gnome/32x32/apps/gnome-terminal.png "[$?] $(alert_helper)"; echo -e "\a"'
#TERM=rxvt-unicode
TERM=rxvt-256color
COLORTERM='rxvt-unicode-256color'


# key bindings
# bindkey "\e[1~" beginning-of-line
# bindkey "\e[4~" end-of-line
# bindkey "\e[5~" beginning-of-history
# bindkey "\e[6~" end-of-history
# bindkey "\e[3~" delete-char
# bindkey "\e[2~" quoted-insert
# bindkey "\e[5C" forward-word
# bindkey "\eOc" emacs-forward-word
# bindkey "\e[5D" backward-word
# bindkey "\eOd" emacs-backward-word
# bindkey "\ee[C" forward-word
# bindkey "\ee[D" backward-word
# bindkey "^H" backward-delete-word
# # for rxvt
# bindkey "\e[8~" end-of-line
# bindkey "\e[7~" beginning-of-line
# # for non RH/Debian xterm, can't hurt for RH/DEbian xterm
# bindkey "\eOH" beginning-of-line
# bindkey "\eOF" end-of-line
# # for freebsd console
# bindkey "\e[H" beginning-of-line
# bindkey "\e[F" end-of-line
# # completion in the middle of a line
# bindkey '^i' expand-or-complete-prefix
