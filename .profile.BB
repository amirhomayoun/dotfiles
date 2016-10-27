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
alias tmux="TERMINFO=~/.terminfo/ LD_LIBRARY_PATH=~/lib:/opt/swt/lib/ ~/local/bin/tmux"
alias urxvt="LD_LIBRARY_PATH=/opt/swt/install/gcc-4.3.2/lib64/:/opt/swt/lib ~/local/bin/urxvt"
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
#alias urxvt=/home/asadough/bin/run_urxvt
alias ccat="pygmentize -g -O style=colorful,linenos=1"

alias big="cd /bb/bigstorn/fixed_income/"
alias mbig="cd /bb/mbig/mbig948"
alias btmsg="cd /bb/mbig/mbig948/myBidea/btid/btmsgsvc"
alias sumun="cd /bb/mbig/mbig948/MUNI/fidelity/sumunsvc"
alias bbds="cd /bb/mbig/mbig948/MUNI/BBDS/munibbds"
alias pick="cd /bb/mbig/mbig948/pick"
alias cntr="cd /bb/mbig/mbig948/pick/pickcntr"
alias data="cd /bb/mbig/mbig948/pick/pickdata"
alias util="cd /bb/mbig/mbig948/pick/pickutil"
alias tick="cd /bb/mbig/mbig948/pick/picktick"
alias shrd="cd /bb/mbig/mbig948/pick/pick_shared"
alias srvx="cd /bb/mbig/mbig948/pick/picksrvx"
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

prqspw(){
    echo "Creating PRQS PW for $1"
    #message="Op1_for_$1"
    #echo $message
    #tt="/bb/admin/ngetprdwin.py -u op1 -d'$message' -i -s $1"
    #tt="/bb/admin/ngetprdwin.py -u op1 -d$message  -i -s $1"
    #tt="/bb/admin/ngetprdwin.py -u op1 -d$(printf "\'Op1 for $1\'")  -i -s $1"
    #tt="/home/asadough/bin/ngetprdwin.py -u op1 -d'Op1 for $1' -i -s $1"

    #tt="/bb/admin/ngetprdwin.py -u op1 -d'Op1 for $1' -i -s $1" #ngetprdwin.py does not exist on on Linux boxes (27 for instance)
    tt="/bb/bin/getprdwin -u op1 -d'Op1 for $1' -i -s $1"
    #echo $tt
    eval $tt
    #$(/home/asadough/bin/ngetprdwin.py -u op1 -d"op1 for $1" -i -s $1)

}
alias pw=prqspw
#alias pw="/bb/admin/ngetprdwin.py -u op1 -d'op1 for $1' -i -s"





###########################################################################
## Terminal settings
###########################################################################
set TERMINFO="$HOME/.terminfo"
#PATH=${PATH}:/bbsrc/training/bin:/opt/swt/bin
PATH=${PATH}:/opt/swt/bin

stty stop ''
stty start ''
stty -ixon
stty -ixoff

EDITOR=vim
#green=$(tput setaf 2)
#red=$(tput setaf 5)
#blue=$(tput setaf 4)
#bold=$(tput bold)
#reset=$(tput sgr0)

#if [[ $ARCH = Linux* ]]; then
#    PS1='\[$red$bold\]\h\[$reset\]:\[$blue$bold\]\w\[$reset\]\$ '
#else
#    PS1='\[$green$bold\]\h\[$reset\]:\[$blue$bold\]\w\[$reset\]\$ '
#fi
##PS1="\\[\e[00;37m\][\[\e[0m\]\[\e[00;31m\]\h\[\e[0m\]\[\e[00;37m\]]\w\\$ \[\e[0m\]"
##PS1="\[\e[00;37m\][\[\e[0m\]\[\e[00;31m\]\h\[\e[0m\]\[\e[00;37m\]]\w\\$ \[\e[0m\]"
##PS1="\[${Yellow}\][\[${Green}\]\u\[${Red}\]@\[${Purple}\]\h\[${Yellow}\]]\[${Cyan}\]\w\[${Color_Off}\]\[\$\] "

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
PATH="$HOME/local/bin:$PATH"

export GREP_COLORS="mc=00;36:ms=31:mt=01;33"
export HISTSIZE=10000
#export TERM=msys

PERL5LIB=/bb/util/common/perlmod/lib/site_perl:/home/asadough/.urxvt/ext; export PERL5LIB
shopt -s extglob

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
#compdef __tmux-sessions tm

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
    if [ -e $(basename $PWD).mk ]; then
        plink $(basename $PWD).mk;
    else 
        plink *.mk;
    fi
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


#eval "$(tmuxifier init -)"

#export http_proxy=http://bproxy.tdmz1.bloomberg.com:80
#export https_proxy=http://bproxy.tdmz1.bloomberg.com:80
#export HTTP_PROXY=http://bproxy.tdmz1.bloomberg.com:80
#export HTTPS_PROXY=http://bproxy.tdmz1.bloomberg.com:80

#export http_proxy=http://devproxy.bloomberg.com:82
#export https_proxy=http://devproxy.bloomberg.com:82

export GIT_SSL_NO_VERIFY=true

#export PATH=~/bin:$PATH

#For Raffaello
export PYTHONPATH=/home/asadough/lib/python/



#BLACK=$(tput setaf 0)
#RED=$(tput setaf 1)
#GREEN=$(tput setaf 2)
#LIME_YELLOW=$(tput setaf 190)
#YELLOW=$(tput setaf 3)
#POWDER_BLUE=$(tput setaf 153)
#BLUE=$(tput setaf 4)
#MAGENTA=$(tput setaf 5)
#CYAN=$(tput setaf 6)
#WHITE=$(tput setaf 7)
#BRIGHT=$(tput bold)
#NORMAL=$(tput sgr0)
#BLINK=$(tput blink)
#REVERSE=$(tput smso)
#UNDERLINE=$(tput smul)

PURPLE="\e[1;35m"
BLUE="\e[0;34m"
LIGHT_BLUE="\e[1;34m"
YELLOW="\e[1;33m"
GREEN="\e[0;32m"
LIGHT_GREEN="\e[1;32m"
RED="\e[1;31m"
WHITE="\e[0m"
CYAN="\e[1;36m"
ORANGE="\e[38;5;208m"
LIGHT_PURPLE="\e[38;5;212m"
PINK="\e[38;5;198m"
GRAY="\e[38;5;60m"
#NORMAL=$(tput sgr0)
NORMAL="\e[0m"
#BRIGHT=$(tput bold)
BRIGHT="\e[1m"

#STAGED_MESSAGE="Changes to be committed:"
#MODIFIED_MESSAGE="Changes not staged for commit:"
#UNTRACKED_MESSAGE="Untracked files:"
#CLEAN_MESSAGE="nothing to commit, working directory clean"
#__load_git_status()
#{
#    if [ -d .git ] || git rev-parse --git-dir > /dev/null 2>&1
#    then
#        #BRANCH=$(git rev-parse --abbrev-ref HEAD 2> /dev/null)
#        BRANCH=$(__git_ps1)
#        #echo -en " ${LIGHT_PURPLE}[$(git rev-parse --abbrev-ref HEAD 2> /dev/null)"
#        echo -en " ${LIGHT_PURPLE}["
#
#        #STATUS="$(git status 2>&1)"
#        #STATUS=${UNTRACKED_MESSAGE}
#        STATUS=${CLEAN_MESSAGE}
#        git diff --quiet || STATUS=${MODIFIED_MESSAGE}
#        git diff --staged --quiet || STATUS=${STAGED_MESSAGE}
#        git ls-files --others --exclude-standard --directory --no-empty-directory --error-unmatch -- ':/*' >/dev/null 2>/dev/null && STATUS=${UNTRACKED_MESSAGE}
#
#
#        if echo "${STATUS}" | grep -q "${CLEAN_MESSAGE}" > /dev/null
#        then
#            echo -en "${GREEN}${BRANCH}"
#        fi
#
#        if echo "${STATUS}" | grep -q "${STAGED_MESSAGE}" > /dev/null
#        then
#            #echo -en "${GREEN}!"
#            echo -en "${CYAN}!${BRANCH}!"
#        fi
#
#        if echo "${STATUS}" | grep -q "${MODIFIED_MESSAGE}" > /dev/null
#        then
#            #echo -en "${YELLOW}!"
#            echo -en "${RED}${BRANCH}"
#        fi
#
#        if echo "${STATUS}" | grep -q "${UNTRACKED_MESSAGE}" > /dev/null
#        then
#            #echo -en "${RED}!"
#            echo -en "${GRAY}${BRANCH}"
#        fi
#
#        echo -en "${LIGHT_PURPLE}]"
#    fi
#}
#

__load_git_status_fast()
{
    #if [[ ${PWD} == ${PREV} ]] #If the directory has not changed, don't do anything
    #then
    #    PS1=${PS1}
    #echo $#
    #echo $HISTCMD
    #if [[ $PWD/ != ~/ && -d .git ]]  || [[ ! -d .git ]]
    if [[ $PWD/ != ~/ && -d .git ]] || git rev-parse --git-dir > /dev/null 2>&1
    then
        BRANCH=$(__git_ps1)
        #BRANCH=$(git rev-parse --abbrev-ref HEAD 2> /dev/null)
        echo -en "${LIGHT_PURPLE}["

        #  staged     modified     untracked
        #    0           0            0          0  clean
        #    0           0            1          1  untracked
        #    0           1            0          2  modified
        #    0           1            1          3  modified+untracked
        #    1           0            0          4  staged
        #    1           0            1          5  staged+untracked
        #    1           1            0          6  staged+modified
        #    1           1            1          7  staged+modified+untracked
        STATE=0 #clean
        #Modified
        git diff --quiet >/dev/null 2>&1 || STATE=$((STATE + 2)) #SLOW
        #Staged
        git diff --staged --quiet >/dev/null 2>&1 || STATE=$((STATE + 4))
        #Untracked
        git ls-files --others --exclude-standard --directory --no-empty-directory --error-unmatch -- ':/*' >/dev/null 2>&1 && STATE=$((STATE + 1))

        ### This is not faster than what I have right here^
        #export GIT_PS1_SHOWDIRTYSTATE=true
        #export GIT_PS1_SHOWUNTRACKEDFILES=true
        #if [[ ${BRANCH} =~ .*\*.* ]]
        #then
        #    STATE=$((STATE+2))
        #fi
        #if [[ ${BRANCH} =~ .*\%.* ]]
        #then
        #    STATE=$((STATE+1))
        #fi
        #if [[ ${BRANCH} =~ .*\+.* ]]
        #then
        #    STATE=$((STATE+4))
        #fi


        #printf $STATE+">"

        ((($STATE==0))) &&  echo -en "${GREEN}"                 # clean
        ((($STATE==1))) &&  echo -en "${YELLOW}!${GREEN}"       # untracked
        ((($STATE==2))) &&  echo -en "${RED}"                   # modified
        ((($STATE==3))) &&  echo -en "${YELLOW}!${RED}"         # modified+untracked
        ((($STATE==4))) &&  echo -en "${CYAN}"                  # staged
        ((($STATE==5))) &&  echo -en "${YELLOW}!${CYAN}"        # staged+untracked
        ((($STATE==6))) &&  echo -en "${RED}!${CYAN}"           # staged+modified
        ((($STATE==7))) &&  echo -en "${RED}!${YELLOW}!${CYAN}" # staged+modified+untracked

        printf "%s" ${BRANCH}
        echo -en "${LIGHT_PURPLE}]"
    else
        echo ""
    fi
    #declare -n ret=$1
    #local message="${PWD}"
    #ret=$message

}

if [[ $ARCH = Linux* ]]; then
    export PS1="\[${PURPLE}\]\[${BRIGHT}\]\h\[$NORMAL\]:\[${BLUE}\]\[${BRIGHT}\]\w\[\$(__load_git_status_fast)\]\[${RED}\]\[${BRIGHT}\] $ \[${WHITE}\]"
else
    export PS1="\[${GREEN}\]\[${BRIGHT}\]\h\[$NORMAL\]:\[${BLUE}\]\[${BRIGHT}\]\w\[\$(__load_git_status_fast)\]\[${RED}\]\[${BRIGHT}\] $ \[${WHITE}\]"
fi

#PS1='\[$red$bold\]\h\[$reset\]:\[$blue$bold\]\w\[$reset\]\$ '
# PS1="\[${WHITE}\](\[${YELLOW}\]\u@\h\[${WHITE}\])\[${NORMAL}\]$ "



#  Customize BASH PS1 prompt to show current GIT repository and branch.
#  by Mike Stewart - http://MediaDoneRight.com

#  SETUP CONSTANTS
#  Bunch-o-predefined colors.  Makes reading code easier than escape sequences.
#  I don't remember where I found this.  o_O

# Reset
Color_Off="\[\033[0m\]"       # Text Reset

# Regular Colors
Black="\[\033[0;30m\]"        # Black
Red="\[\033[0;31m\]"          # Red
Green="\[\033[0;32m\]"        # Green
Yellow="\[\033[0;33m\]"       # Yellow
Blue="\[\033[0;34m\]"         # Blue
Purple="\[\033[0;35m\]"       # Purple
Cyan="\[\033[0;36m\]"         # Cyan
White="\[\033[0;37m\]"        # White

# Bold
BBlack="\[\033[1;30m\]"       # Black
BRed="\[\033[1;31m\]"         # Red
BGreen="\[\033[1;32m\]"       # Green
BYellow="\[\033[1;33m\]"      # Yellow
BBlue="\[\033[1;34m\]"        # Blue
BPurple="\[\033[1;35m\]"      # Purple
BCyan="\[\033[1;36m\]"        # Cyan
BWhite="\[\033[1;37m\]"       # White

# Underline
UBlack="\[\033[4;30m\]"       # Black
URed="\[\033[4;31m\]"         # Red
UGreen="\[\033[4;32m\]"       # Green
UYellow="\[\033[4;33m\]"      # Yellow
UBlue="\[\033[4;34m\]"        # Blue
UPurple="\[\033[4;35m\]"      # Purple
UCyan="\[\033[4;36m\]"        # Cyan
UWhite="\[\033[4;37m\]"       # White

# Background
On_Black="\[\033[40m\]"       # Black
On_Red="\[\033[41m\]"         # Red
On_Green="\[\033[42m\]"       # Green
On_Yellow="\[\033[43m\]"      # Yellow
On_Blue="\[\033[44m\]"        # Blue
On_Purple="\[\033[45m\]"      # Purple
On_Cyan="\[\033[46m\]"        # Cyan
On_White="\[\033[47m\]"       # White

# High Intensty
IBlack="\[\033[0;90m\]"       # Black
IRed="\[\033[0;91m\]"         # Red
IGreen="\[\033[0;92m\]"       # Green
IYellow="\[\033[0;93m\]"      # Yellow
IBlue="\[\033[0;94m\]"        # Blue
IPurple="\[\033[0;95m\]"      # Purple
ICyan="\[\033[0;96m\]"        # Cyan
IWhite="\[\033[0;97m\]"       # White

# Bold High Intensty
BIBlack="\[\033[1;90m\]"      # Black
BIRed="\[\033[1;91m\]"        # Red
BIGreen="\[\033[1;92m\]"      # Green
BIYellow="\[\033[1;93m\]"     # Yellow
BIBlue="\[\033[1;94m\]"       # Blue
BIPurple="\[\033[1;95m\]"     # Purple
BICyan="\[\033[1;96m\]"       # Cyan
BIWhite="\[\033[1;97m\]"      # White

# High Intensty backgrounds
On_IBlack="\[\033[0;100m\]"   # Black
On_IRed="\[\033[0;101m\]"     # Red
On_IGreen="\[\033[0;102m\]"   # Green
On_IYellow="\[\033[0;103m\]"  # Yellow
On_IBlue="\[\033[0;104m\]"    # Blue
On_IPurple="\[\033[10;95m\]"  # Purple
On_ICyan="\[\033[0;106m\]"    # Cyan
On_IWhite="\[\033[0;107m\]"   # White

# Various variables you might want for your PS1 prompt instead
Time12h="\T"
Time12a="\@"
PathShort="\w"
PathFull="\W"
NewLine="\n"
Jobs="\j"


# This PS1 snippet was adopted from code for MAC/BSD I saw from: http://allancraig.net/index.php?option=com_content&view=article&id=108:ps1-export-command-for-git&catid=45:general&Itemid=96
# I tweaked it to work on UBUNTU 11.04 & 11.10 plus made it mo' better

#export PS1=$IBlack$Time12h$Color_Off'$(git branch &>/dev/null;\
#if [ $? -eq 0 ]; then \
#  echo "$(echo `git status --porcelain -uno` | grep "nothing to commit" > /dev/null 2>&1; \
#  if [ "$?" -eq "0" ]; then \
#    # @4 - Clean repository - nothing to commit
#    echo "'$Green'"$(__git_ps1 " (%s)"); \
#  else \
#    # @5 - Changes to working tree
#    echo "'$IRed'"$(__git_ps1 " {%s}"); \
#  fi) '$BYellow$PathShort$Color_Off'\$ "; \
#else \
#  # @2 - Prompt when not in GIT repo
#  echo " '$Yellow$PathShort$Color_Off'\$ "; \
#fi)'




#source ~/.git-completion.bash
