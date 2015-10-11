# ~/.profile skeleton
# ~/.profile runs on interactive login shells if it exists
# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
echo "PROFILE has run"

#GROUP=fixed-income-group


# =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
# chimera not present/didn't run, set some basic stuff up
# hope /etc/passwd is good enough
if [ ! "$BBENV" ]
then
     PS1="${HOSTNAME}:\${PWD} \$ "
     ##PATH=$PATH:/home/asadough/bin:/usr/sbin
     PATH=$PATH:/usr/sbin
     ##LPDEST=put_your_printer_here
     ##GROUP=put_your_group_here
     stty erase \^\h kill \^u intr \^c
     stty echoe echok ixon ixoff -ixany

     if [ $(uname) = "SunOS" ] && [ ! "$BASH" ]
     then
          set -o emacs
          alias __A=$(print '\0020') # ^P = up = previous command
          alias __B=$(print '\0016') # ^N = down = next command
          alias __C=$(print '\0006') # ^F = right = forward a character
          alias __D=$(print '\0002') # ^B = left = back a character
          alias __H=$(print '\0001') # ^A = home = beginning of line
          stty erase ^?
          #echo "SunOS keys set"
     fi
fi

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
#alias vi='vim'
#alias vim="vim -X"
vim(){
    if [[ $ARCH = SunOS* ]]; then
        command /opt/bb/bin/vim "$@"
    else 
        command /home/asadough/bin/vim "$@"
    fi
}

alias grep='grep --color'
alias urxvt=/home/asadough/bin/run_urxvt
alias ccat="pygmentize -g -O style=colorful,linenos=1"

alias bigstore="cd /bb/bigstorn/fixed_income/asadough/"
alias mbig="cd /bb/mbig/mbig948"
alias muni="cd /bb/mbig/mbig948/muniverse"
alias btmsg="cd /bb/mbig/mbig948/myBidea/btid/btmsgsvc"
alias sumun="cd /bb/mbig/mbig948/MUNI/fidelity/sumunsvc"
alias mncdr="cd /bb/mbig/mbig948/MUNI/fidelity/mncdrsvc"
alias work4="cd /bb/mbig/mbig948/tmsg"
alias bbds="cd /bb/mbig/mbig948/MUNI/BBDS/munibbds"
alias pica="cd /bb/mbig/mbig948/MUNI/pckausvc"
alias req="vim *req*proc*.cpp"
alias ssh1="ssh sundev1"
alias ssh2="ssh sundev2"
alias ssh9="ssh sundev9"
alias ssh13="ssh sundev13"
alias ssh15="ssh sundev15"

alias pp="not;dirPlink;ton"
alias ppc="not; dirPlinkClean; dirPlink; ton"
#alias pp="not;plink btmsgsvc.mk ;ton"
#alias ppc="not;plink btmsgsvc.mk clean;ton"


if ! { [  -n "$TMUX" ]; } then
    #Not in tmux, attach to weechat session, if not there, create it
    alias wechat="SESSIONNAME='weechat' && (tmux attach-session -t $SESSIONNAME || tmux new-session -s $SESSIONNAME)"
else
    #In tmux. Switch to the weechat session. If not there, create it and switch to it.
    #TMUX= exists so tmux doesn't throw a sessions should be nested with care
    alias wechat="tmux switch-client -t weechat || TMUX= tmux new-session -d -s weechat; tmux switch-client -t weechat"
fi


###########################################################################
## Terminal settings
###########################################################################
set TERMINFO="$HOME/.terminfo"
#PATH=${PATH}:/bbsrc/training/bin:/opt/swt/bin
PATH=${PATH}:/opt/swt/bin

EDITOR=vim
green=$(tput setaf 2)
blue=$(tput setaf 4)
bold=$(tput bold)
reset=$(tput sgr0)
PS1='\[$green$bold\]\h\[$reset\]:\[$blue$bold\]\w\[$reset\]\$ '
#PS1="\\[\e[00;37m\][\[\e[0m\]\[\e[00;31m\]\h\[\e[0m\]\[\e[00;37m\]]\w\\$ \[\e[0m\]"
#PS1="\[\e[00;37m\][\[\e[0m\]\[\e[00;31m\]\h\[\e[0m\]\[\e[00;37m\]]\w\\$ \[\e[0m\]"
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
PATH="$PATH:$HOME/.tmuxifier/bin"

export GREP_COLORS="mc=00;36:ms=31:mt=01;33"
export HISTSIZE=10000
#export TERM=msys

PERL5LIB=/bb/util/common/perlmod/lib/site_perl:/home/asadough/.urxvt/ext; export PERL5LIB

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
    else if [[ $ARCH = AIX* ]]; then
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
    #while (true); do clear; echo "Monitoring $1 every $sleepTime seconds"; /bin/ls -ltrsh $1; sleep $sleepTime; done
    while (true); do clear; echo "Monitoring $1 every $sleepTime seconds"; ls -ltrsh $1; sleep $sleepTime; done
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

tmuxHashColor() {
  local hsh=$(echo $1 | cksum | cut -d ' ' -f 1)
  local num=$(expr $hsh % 255)
  echo "colour$num"
}
sendKeysToTmux() {
    #fgcolor="blue"
    #bgcolor="red"
    status_fgcolor=$2
    status_bgcolor=$3
    pane_color=$4
    pane_border_color=$5
    #local color=$(tmuxHashColor $1)
    echo $status_fgcolor
    echo $status_bgcolor
    echo $pane_color
    echo $pane_border_color
    tmux send-keys -t $1 "tmux set-option status-fg $status_fgcolor" C-m
    tmux send-keys -t $1 "tmux set-option status-bg $status_bgcolor" C-m
    tmux send-keys -t $1 "tmux set-window-option -g window-status-fg $status_fgcolor" C-m
    tmux send-keys -t $1 "tmux set-window-option -g window-status-bg $status_bgcolor" C-m
    tmux send-keys -t $1 "tmux set -g pane-border-fg $pane_color" C-m
    tmux send-keys -t $1 "tmux set -g pane-border-bg $pane_color" C-m
    tmux send-keys -t $1 "tmux set -g pane-active-border-fg $pane_border_color" C-m
    tmux send-keys -t $1 "tmux set -g pane-active-border-bg $pane_border_color" C-m
    tmux send-keys -t $1 "clear" C-m

}
function splitTm() {
numWin=$1
runCom=${2}
for i in `seq 1 $numWin`
do
tmux select-pane -t $i
tmux send-keys -t $i "clear" C-m
tmux send-keys -t $i "This is pane $i" C-m
#tmux send-keys -t $i "/bb/bin/getprdwin -i" C-m
tmux send-keys -t $i "${runCom}" C-m
if [ $((i%2)) -eq 0 ];then
    tmux split-window -h
else
    tmux split-window -v

fi

lastPane=$i
sleep 1
done

#this goes to each pane and sends a command, in this case - just identifies the pane.

#tmux select-pane -t 1
#tmux send-keys -t 1 "This is pane 1" C-m
#tmux select-pane -t 2
#tmux send-keys -t 2 "This is pane 2" C-m
#tmux select-pane -t 3
#tmux send-keys -t 3 "This is pane 3" C-m
lastPane2=`expr  $lastPane + 1`
echo $lastPane2
tmux select-pane -t $lastPane2
This is pane 1
ls

tmux kill-pane
tmux select-layout tiled
#tmux send-keys -t $lastPane2  "KILL me" C-m
}
function tmProd() {
    if ! { [  -n "$TMUX" ]; } then
        echo "Not in TMUX. Please run me in tmux"
        return
    fi
    sessionName=`tmux display-message -p '#S'`
    echo "This session is called $sessionName"
    #if [[ $sessionName != "prod" ]] && [[ $sessionName != "prod2" ]]; then
    #    echo "Run me only in a session named prod"
    #    return
    #fi


    numWin=0
    numWin=$1
    if [ -z $1 ]; then
        numWin=10
    fi
    echo $numWin
    #runCom="ls -ltr"
    runCom="/bb/bin/getprdwin -i"
    splitTm $numWin "${runCom}" 

}

ns() {
    sName=$1
    if [ -z $1 ]; then
        sName=$(basename $(pwd))
        echo $sName
    fi

    if [[ $sName = "prod" || $sName = "prod2" ]]; then
        if ! { [  -n "$TMUX" ]; } then
            echo "Not in tmux, attaching to $sName If not there, will create it"
            tmux attach-session -t $sName || tmux new-session -d -s $sName
            sendKeysToTmux $sName "red" "red" "red" "blue"
            # tmux send-keys -t $sName "tmProd" C-m
            tmux attach -t $sName

        else
            echo "In tmux, attaching to $sName If not there, will create it"
            #In tmux. Switch to the $sName session. If not there, create it and switch to it.
            #TMUX= exists so tmux doesn't throw a sessions should be nested with care
            tmux switch-client -t $sName || TMUX= tmux new-session -d -s $sName;
            sendKeysToTmux $sName "red" "red" "red" "blue"
            # tmux send-keys -t $sName "tmProd" C-m
            tmux switch-client -t $sName
        fi
    else
        if ! { [  -n "$TMUX" ]; } then
            echo "Not in tmux, attaching to $sName If not there, will create it"
            tmux attach-session -t $sName || tmux new-session -d -s $sName
            sendKeysToTmux $sName "default" "default" "colour178" "blue"
            tmux attach -t $sName
        else
            echo "In tmux, attaching to $sName If not there, will create it"
            #TMUX= exists so tmux doesn't throw a sessions should be nested with care
            tmux switch-client -t $sName || TMUX= tmux new-session -d -s $sName;
            sendKeysToTmux $sName "default" "default" "colour178" "blue"
            tmux switch-client -t $sName
        fi
    fi

    #tmux new-session -d -s $sName

}


eval "$(tmuxifier init -)"

export GIT_SSL_NO_VERIFY=true

export PATH=~/bin:$PATH

#For Raffaello
export PYTHONPATH=/home/asadough/lib/python/
