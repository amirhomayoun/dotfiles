
alias ls="/opt/swt/install/coreutils-8.21-64/bin/ls --color="auto""
alias tmux="TERMINFO=~/.terminfo/ LD_LIBRARY_PATH=~/lib:/opt/swt/lib/  ~/bin/tmux"
alias la="ls -la"
set TERMINFO="$HOME/.terminfo"

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
alias urxvt=/home/asadough/bin/run_urxvt
alias work="cd /bb/mbig/mbig948/btmsgsvc/"
#alias vim="vim -X"
alias req="vim *req*proc*.cpp" 
alias ssh1="ssh sundev1"
alias ssh2="ssh sundev2"
alias ssh9="ssh sundev9"

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
