""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
"   Filename: .vimrc                                                         "
" Maintainer: Amir H. Sadoughi						     "
"        URL: http://github.com/amirhomayoun/dotfiles	                     "
"                                                                            "
"                                                                            "
" Sections:                                                                  "
"   01. General ................. General Vim behavior                       "
"   02. Events .................. General autocmd events                     "
"   03. Theme/Colors ............ Colors, fonts, etc.                        "
"   04. Vim UI .................. User interface behavior                    "
"   05. Text Formatting/Layout .. Text, tab, indentation related             "
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""


""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" 01. General                                                                "
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
set nocompatible         " get rid of Vi compatibility mode. SET FIRST!
set so =10
set term=xterm-256color  " set term
"set relativenumber  
set visualbell
noremap <expr> k ((line('.')==1)?'':'k')
noremap <expr> j ((line('.')==line('$'))?'':'j')
set noerrorbells
set novisualbell
"Slow start up of vim because of accessing X clipboard
set clipboard=autoselect,exclude:cons\\\|linux\\\|screen 
if $DISPLAY =~ '\(\(cos\|scs\)\d\+nai\d\+\)\|\(spkpc\d\+\)\|\(tc-garyjohn\)' 
    set clipboard=autoselect,exclude:.* 
endif 
set clipboard=exclude:*

""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" 02. Events                                                                 "
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
execute pathogen#infect()

filetype plugin indent on " filetype detection[ON] plugin[ON] indent[ON]

set omnifunc=syntaxcomplete#Complete

" In Makefiles DO NOT use spaces instead of tabs
" autocmd FileType make setlocal noexpandtab
" In Ruby files, use 2 spaces instead of 4 for tabs
" autocmd FileType ruby setlocal sw=2 ts=2 sts=2

" Enable omnicompletion (to use, hold Ctrl+X then Ctrl+O while in Insert mode.
" set ofu=syntaxcomplete#Complete
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" 03. Theme/Colors                                                           "
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
set t_Co=256              " enable 256-color mode.
syntax enable             " enable syntax highlighting (previously syntax on).
"color desert       " set colorscheme
color delek       " set colorscheme
"color elflord       " set colorscheme

" Prettify JSON files
"autocmd BufRead,BufNewFile *.json set filetype=json
"autocmd Syntax json sou ~/.vim/syntax/json.vim

" Prettify Vagrantfile
" :autocmd BufRead,BufNewFile Vagrantfile set filetype=ruby

" Highlight characters that go over 80 columns
" highlight OverLength ctermbg=red ctermfg=white guibg=#592929
" match OverLength /\%81v.\+/

" Show a warning color for columns 77-80 and error color for over 80 chars.
"au BufWinEnter * let w:m1=matchadd('Search', '\%<81v.\%>77v', -1)
"au BufWinEnter * let w:m2=matchadd('ErrorMsg', '\%>80v.\+', -1)


"color schema for visual mode
hi Visual term=reverse cterm=reverse


""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" 04. Vim UI                                                                 "
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
set number                " show line numbers
set nocul                   " highlight current line
set laststatus=2          " last window always has a statusline
set hlsearch            " Don't continue to highlight searched phrases.
set incsearch             " But do highlight as you type your search.
"set ignorecase            " Make searches case-insensitive.
set smartcase
set ruler                 " Always show info along bottom.
set showmatch
set statusline=%<%f\%h%m%r%=%-20.(line=%l\ \ col=%c%V\ \ totlin=%L%)\ \ \%h%m%r%=%-40(bytval=0x%B,%n%Y%)\%P
set statusline=
set statusline +=%1*\ %n\ %*            "buffer number
set statusline +=%5*%{&ff}%*            "file format
set statusline +=%3*%y%*                "file type
set statusline +=%4*\ %<%F%*            "full path
set statusline +=%2*%m%*                "modified flag
set statusline +=%1*%=%5l%*             "current line
set statusline +=%2*/%L%*               "total lines
set statusline +=%1*%4v\ %*             "virtual column number
set statusline +=%2*0x%04B\ %*          "character under cursor

" default the statusline to green when entering Vim
hi statusline guibg=DarkGrey ctermfg=8 guifg=White ctermbg=15

hi User1 guifg=#eea040 guibg=#222222
hi User2 guifg=#dd3333 guibg=#222222
hi User3 guifg=#ff66ff guibg=#222222
hi User4 guifg=#a0ee40 guibg=#222222
hi User5 guifg=#eeee40 guibg=#222222

"set cul                                           " highlight current line
"hi CursorLine term=none cterm=none ctermbg=3      " adjust color
set modeline
set ls=2
set cindent
:ab #b /****************************************
:ab #e ^V^H*****************************************/
" set clipboard=unnamed " It is apparently for Window$
set clipboard=unnamedplus
set nu

""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" 05. Text Formatting/Layout                                                 "
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
set autoindent            " auto-indent
set tabstop=4             " tab spacing
set softtabstop=4         " unify
set shiftwidth=4          " indent/outdent by 4 columns
set shiftround            " always indent/outdent to the nearest tabstop
set expandtab             " use spaces instead of tabs
set smarttab              " use tabs at the start of a line, spaces elsewhere
set wrap                " don't wrap text
set textwidth=80

""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" 06. Misc
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
let g:miniBufExplMapWindowNavVim = 1        "minibufexplorer 
let g:miniBufExplMapWindowNavArrows = 1     "minibufexplorer 
let g:miniBufExplMapCTabSwitchBufs = 1      "minibufexplorer 
let g:miniBufExplModSelTarget = 1           "minibufexplorer  

"map <F2> :ls<CR>:b<Space>           "swich buffers
set tags=tags;/                     "ctag

"if has('mouse')
"    set mouse=a
"endif
"

set backspace=indent,eol,start
set t_kb= 
fixdel
"This unsets the last search pattern register by hitting return
nnoremap <CR> :noh<CR><CR>

"For omnicppcomplete
set nocp
filetype plugin on

"Use F4 to switch between source and heade (cpp and h)
map <F4> :e %:p:s,.h$,.X123X,:s,.cpp$,.h,:s,.X123X$,.cpp,<CR>

set ttyfast

" Use jj instead of Esc

imap jj <Esc>
set timeoutlen=500

" Status bar color
"
function! InsertStatuslineColor(mode)
    if a:mode == 'i'
        hi statusline guibg=Cyan ctermfg=6 guifg=Black ctermbg=0
    elseif a:mode == 'r'
        hi statusline guibg=Purple ctermfg=5 guifg=Black ctermbg=0
    else
        hi statusline guibg=DarkRed ctermfg=1 guifg=Black ctermbg=0
    endif
endfunction

au InsertEnter * call InsertStatuslineColor(v:insertmode)
"au InsertLeave * hi statusline guibg=DarkGrey ctermfg=8 guifg=White ctermbg=15
au InsertLeave * hi statusline guibg=Green ctermfg=8 guifg=White ctermbg=15

" default the statusline to green when entering
"Vim
hi statusline guibg=DarkGrey ctermfg=8
autocmd BufEnter * :syntax sync fromstart


"guifg=White ctermbg=15

" Formats the statusline
"set statusline=%f   " file name
"set statusline+=[%{strlen(&fenc)?&fenc:'none'},
"file encoding
"set statusline+=%{&ff}] "file format
"set statusline+=%y      "filetype
"set statusline+=%h      "help file flag
"set statusline+=%m      "modified flag
"set statusline+=%r      "read only flag

" Puts in the current git status
"if count(g:pathogen_disabled, 'Fugitive')
"    < 1   
"    set
"    statusline+=%{fugitive#statusline()}
"endif

" Puts in syntastic warnings
"if count(g:pathogen_disabled,
"    'Syntastic') < 1  
"    set
"    statusline+=%#warningmsg#
"    set
"    statusline+=%{SyntasticStatuslineFlag()}
"    set
"    statusline+=%*
"endif
"set statusline+=\ %=                        " align left
"set statusline+=Line:%l/%L[%p%%]            " line X of Y [percent of file]
"set statusline+=\ Col:%c                    " current column
"set statusline+=\ Buf:%n                    " Buffer number
"set statusline+=\ [%b][0x%B]\               " ASCII and byte code under cursor
"if $COLORTERM == 'urxvt'
"      set t_Co=256
"  endif



""
"" FuzzyFinder key mappings 
""

let g:fuf_modesDisable = []
let g:fuf_mrufile_maxItem = 1000
let g:fuf_mrucmd_maxItem = 400
let g:fuf_mrufile_exclude = '\v\~$|\.(bak|sw[po])$|^(\/\/|\\\\|\/mnt\/)'
nnoremap <silent> <C-n>      :FufBuffer<CR>
nnoremap <silent> <C-p>      :FufFileWithCurrentBufferDir<CR>
nnoremap <silent> <C-f><C-p> :FufFileWithFullCwd<CR>
nnoremap <silent> <C-f>p     :FufFile **/<CR>
"nnoremap <silent> <C-f>p     :FufFile<CR>
nnoremap <silent> <C-f><C-d> :FufDirWithCurrentBufferDir<CR>
nnoremap <silent> <C-f>d     :FufDirWithFullCwd<CR>
nnoremap <silent> <C-f>D     :FufDir<CR>
nnoremap <silent> <C-j>      :FufMruFile<CR>
nnoremap <silent> <C-k>      :FufMruCmd<CR>
nnoremap <silent> <C-b>      :FufBookmarkDir<CR>
nnoremap <silent> <C-f><C-t> :FufTag<CR>
nnoremap <silent> <C-f>t     :FufTag!<CR>
noremap  <silent> g]         :FufTagWithCursorWord!<CR>
nnoremap <silent> <C-f><C-f> :FufTaggedFile<CR>
nnoremap <silent> <C-f><C-j> :FufJumpList<CR>
nnoremap <silent> <C-f><C-g> :FufChangeList<CR>
nnoremap <silent> <C-f><C-q> :FufQuickfix<CR>
nnoremap <silent> <C-f><C-l> :FufLine<CR>
nnoremap <silent> <C-f><C-h> :FufHelp<CR>
nnoremap <silent> <C-f><C-b> :FufAddBookmark<CR>
vnoremap <silent> <C-f><C-b> :FufAddBookmarkAsSelectedText<CR>
nnoremap <silent> <C-f><C-e> :FufEditInfo<CR>
nnoremap <silent> <C-f><C-r> :FufRenewCache<CR>

nmap <F11> :!find . -iname '*.c' -o -iname '*.cpp' -o -iname '*.h' -o -iname '*.hpp' > cscope.files ;
          \:!cscope -b -i cscope.files -f cscope.out<CR>
          \:cs kill -1<CR>:cs add cscope.out<CR>
source ~/.vim/bundle/cscope_macros/cscope_macros.vim 
