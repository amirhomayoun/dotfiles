# ~/.profile skeleton
# ~/.profile runs on interactive login shells if it exists
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
echo "PROFILE has run"

###########################################################################
## Aliases for linux command line
###########################################################################

alias ls="/opt/swt/install/coreutils-8.21-64/bin/ls --color="auto""
alias tmux="TERMINFO=~/.terminfo/ LD_LIBRARY_PATH=~/lib:/opt/swt/lib/ ~/bin/tmux"
alias la="ls -lah"
alias cl="clear"
alias psep="psef"
#alias ls="ls --color -h --group-directories-first"
#alias la="ls --color -h -la --group-directories-first"
alias vi='vim'
#alias vim="vim -X"
alias grep='grep --color'

alias mbig="cd /bb/mbig/mbig948"
alias work4="cd /bb/mbig/mbig948/tmsg"
alias req="vim *req*proc*.cpp" 
alias ssh1="ssh sundev1"
alias ssh2="ssh sundev2"
alias ssh9="ssh sundev9"
alias ssh13="ssh sundev13"

alias pp="not;dirPlink;ton"
alias ppc="not; dirPlinkClean; dirPlink; ton"
#alias pp="not;plink btmsgsvc.mk ;ton"
#alias ppc="not;plink btmsgsvc.mk clean;ton"



###########################################################################
## Terminal settings
###########################################################################
set TERMINFO="$HOME/.terminfo"
#PATH=${PATH}:/bbsrc/training/bin:/opt/swt/bin
PATH=${PATH}:/opt/swt/bin

EDITOR=vim
PS1="\[\e[00;37m\][\[\e[0m\]\[\e[00;31m\]\h\[\e[0m\]\[\e[00;37m\]]\w\\$ \[\e[0m\]"
#PS1="\[${Yellow}\][\[${Green}\]\u\[${Red}\]@\[${Purple}\]\h\[${Yellow}\]]\[${Cyan}\]\w\[${Color_Off}\]\[\$\] "

if [[ $ARCH = SunOS* ]]; then
#    alias ls="/opt/swt/install/coreutils-8.12/bin/ls --color"
    eval `dircolors ~/.dir_colors`
#else
#    alias ls="/opt/swt/install/coreutils-8.21-64/bin/ls --color="auto""
#    alias ls="ls --color"
#    PATH="$PATH":~/bin
fi
eval `dircolors ~/.dir_colors`

PATH="$PATH:$HOME/urxvt/usr/local/lib"
export GREP_COLORS="mc=00;36:ms=31:mt=01;33" 
export HISTSIZE=10000
#export TERM=msys

###########################################################################
## Tmux settings
###########################################################################
# ssh wrapper that rename current tmux window to the hostname of the
# remote host.
case $TERM in
    xterm*|rxvt|rxvt-256color)
        #PROMPT_COMMAND='echo -ne "\033]0;${USER}@${HOSTNAME}\007"'
        PROMPT_COMMAND='echo -ne "\033]0;${HOSTNAME}\007"'
        export PROMPT_COMMAND
        ;;
    screen)
      TITLE=$(hostname -s)                                                      
      PROMPT_COMMAND='echo -ne "\033k${TITLE}\033\\"'                      
      export PROMPT_COMMAND
        ;;
esac

#function to pull out the latest tmux showenv and update itself accordingly
tmup () 
{ 
    echo -n "Updating to latest tmux environment...";
    export IFS=",";
    for line in $(tmux showenv -t $(tmux display -p "#S") | tr "\n" ",");
    do
        if [[ $line == -* ]]; then
            unset $(echo $line | cut -c2-);
        else
            export $line;
        fi;
    done;
    unset IFS;
    echo "Done"
}
#alias done='echo '"'"'I am done!'"'"'>~/.tmuxAlert'
window=$(tmux display-message -p '#I');
ton() 
{
    #echo "[WINDOW:$window]  I am done! [$(date +%H:%M)]">~/.tmuxAlert;
    echo "Stop: [$(date +%H:%M)]">~/.tmuxAlertStop;
}
export -f ton

not() 
{
    echo "" >~/.tmuxAlertStop;
    echo "Start: [$(date +%H:%M)]">~/.tmuxAlertStart;
}
export -f not

function tm() 
{
    [[ -z "$1" ]] && { echo "usage: tm <session>" >&2; return 1; }
    tmux has -t $1 && tmux attach -t $1 || tmux new -s $1
}

function __tmux-sessions() 
{
    local expl
    local -a sessions
    sessions=( ${${(f)"$(command tmux list-sessions)"}/:[ $'\t']##/:} )
    _describe -t sessions 'sessions' sessions "$@"
}
compdef __tmux-sessions tm

function tmw 
{
    tmux split-window -dh "$*"
}
log()
{
    echo "";
}
###########################################################################
## Functions
###########################################################################

dirPlink()
{
    plink $(basename $PWD).mk;
}

dirPlinkClean()
{
    plink $(basename $PWD).mk clean;
}

run()
{
    if [[ $ARCH = SunOS* ]]; then
        ./$(basename $PWD).sundev1.tsk $(basename $PWD).cfg;
    else if [[$ARCH = AIX* ]]; then
        ./$(basename $PWD).ibm.tsk $(basename $PWD).cfg;
    fi
fi
}  

runp()
{
    if [[ $ARCH = SunOS* ]]; then
        ./$(basename $PWD).sundev1.tsk mbig948;
    else if [[ $ARCH = AIX* ]]; then
        ./$(basename $PWD).ibm.tsk mbig948;
    fi
fi
}


man() {
    env LESS_TERMCAP_mb=$'\E[01;31m' \
    LESS_TERMCAP_md=$'\E[01;38;5;74m' \
    LESS_TERMCAP_me=$'\E[0m' \
    LESS_TERMCAP_se=$'\E[0m' \
    LESS_TERMCAP_so=$'\E[38;5;246m' \
    LESS_TERMCAP_ue=$'\E[0m' \
    LESS_TERMCAP_us=$'\E[04;38;5;146m' \
    man "$@"
}

#. /bbsrc/princeton/skunk/vim/bb_include_path_cursor_vim source
lsloop(){
    if [ -z "$1" ]
    then
        $1 = ls *
    fi
    if [ -z "$2" ]
    then
        sleepTime=1
    else
        sleepTime=$2
    fi
    while (true); do clear; echo "Monitoring $1 every $sleepTime seconds"; /bin/ls -ltrsh $1; sleep $sleepTime; done
}

comloop(){
    if [ -z "$1" ]
    then
        $1 = ls *
    fi
    if [ -z "$2" ]
    then
        sleepTime=1
    else
        sleepTime=$2
    fi
    while (true); do clear; echo "Running $1 every $sleepTime seconds"; $1; sleep $sleepTime; done
}


settitle() {
        printf "\033k$1\033\\"
    }
