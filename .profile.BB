alias ls="/opt/swt/install/coreutils-8.21-64/bin/ls --color="auto""
alias tmux="TERMINFO=~/.terminfo/ LD_LIBRARY_PATH=~/lib:/opt/swt/lib/  ~/bin/tmux"
alias la="ls -lah"
#alias ls="ls --color -h --group-directories-first"
#alias la="ls --color -h -la --group-directories-first"

set TERMINFO="$HOME/.terminfo"
PATH=${PATH}:/bbsrc/training/bin:/opt/swt/bin

EDITOR=vim
PS1="\[${Yellow}\][\[${Green}\]\u\[${Red}\]@\[${Purple}\]\h\[${Yellow}\]]\[${Cyan}\]\w\[${Color_Off}\]\[\$\] "
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

#alias vim="vim -X"
alias req="vim *req*proc*.cpp" 
alias ssh1="ssh sundev1"
alias ssh2="ssh sundev2"
alias ssh9="ssh sundev9"
alias ssh13="ssh sundev13"

alias pp="not;dirPlink;ton"
alias ppc="not; dirPlinkClean; dirPlink; ton"

export HISTSIZE=5000
#export TERM=msys


settitle() {
        printf "\033k$1\033\\"
    }

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
    echo "I am done! [$(date +%H:%M)]">~/.tmuxAlert;
}
not() 
{
    echo "Start: [$(date +%H:%M)]">~/.tmuxAlert;
}
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
