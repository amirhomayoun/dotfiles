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
"   06. Key mappings 
"   07. Plugins...................Plugin specific settings                   "
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""


""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" 01. General                                                                "
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
set nocompatible         " get rid of Vi compatibility mode. SET FIRST!
set so =10
set term=xterm-256color  " set term
"set relativenumber  
set visualbell
set noerrorbells
set novisualbell
"Slow start up of vim because of accessing X clipboard
set clipboard=autoselect,exclude:cons\\\|linux\\\|screen 
if $DISPLAY =~ '\(\(cos\|scs\)\d\+nai\d\+\)\|\(spkpc\d\+\)\|\(tc-garyjohn\)' 
    set clipboard=autoselect,exclude:.* 
endif 
set clipboard=exclude:*
set lazyredraw
set ttyfast

if has('mouse')
    set mouse=a
endif

"For omnicppcomplete
set nocp

set backspace=indent,eol,start
set t_kb= 
fixdel
let g:pymode_paths=['/home/asadough/bin/Python-2.6.2']

set diffopt+=iwhite

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
color molokai       " set colorscheme

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
"set statusline=%<%f\%h%m%r%=%-20.(line=%l\ \ col=%c%V\ \ totlin=%L%)\ \ \%h%m%r%=%-40(bytval=0x%B,%n%Y%)\%P
"set statusline=
"set statusline +=%1*\ %n\ %*            "buffer number
"set statusline +=%5*%{&ff}%*            "file format
"set statusline +=%3*%y%*                "file type
"set statusline +=%4*\ %<%F%*            "full path
"set statusline +=%2*%m%*                "modified flag
"set statusline +=%1*%=%5l%*             "current line
"set statusline +=%2*/%L%*               "total lines
"set statusline +=%1*%4v\ %*             "virtual column number
"set statusline +=%2*0x%04B\ %*          "character under cursor

" default the statusline to green when entering Vim
" hi statusline guibg=DarkGrey ctermfg=8 guifg=White ctermbg=15

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
set wrap                  " don't wrap text
set textwidth=80

""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" 06. Key mappings
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
noremap <expr> k ((line('.')==1)?'':'k')
noremap <expr> j ((line('.')==line('$'))?'':'j')
"let mapleader="\"

"copy/paste to external clipboard
nnoremap <silent> <C-c> maggvG$"+y'a

"This unsets the last search pattern register by hitting return
nnoremap <CR> :noh<CR><CR>

"Moving to another split
nnoremap <Leader>w <C-w>w

"map <F2> :ls<CR>:b<Space>           "swich buffers

"Use F4 to switch between source and heade (cpp and h)
map <F4> :e %:p:s,.h$,.X123X,:s,.cpp$,.h,:s,.X123X$,.cpp,<CR>
map <F5> :e %:p:s,.h$,.X123X,:s,.c$,.h,:s,.X123X$,.c,<CR>

"Use <F6> to switch to the next split
:map <F6> <C-W>w

" Use jj instead of Esc
imap jj <Esc>
set timeoutlen=500

"map * *n 
"map # *N 
nnoremap * :let @/='\<<C-R>=expand("<cword>")<CR>\>'<CR>:set hls<CR>
nnoremap # :let @/='\<<C-R>=expand("<cword>")<CR>\>'<CR>:set hls<CR>
map N Nzz
map n nzz

nmap <F11> :!ctags -R .&<CR>
          \:!find . -iname "*.c" -o -iname "*.cpp" -o -iname "*.h" -o -iname "*.hpp" > cscope.files&<CR>
          \:!cscope -b -i cscope.files -f cscope.out<CR>
          \:cscope kill -1<CR>
          \:cscope add cscope.out<CR>

" Taglist keybindings
nnoremap <silent> <F8> :TlistToggle<CR>
nnoremap <silent> <F9> :TlistOpen<CR>

" Use ctrl-[hjkl] to select the active split!
nmap <silent> <c-k> :wincmd k<CR>
nmap <silent> <c-j> :wincmd j<CR>
nmap <silent> <c-h> :wincmd h<CR>
nmap <silent> <c-l> :wincmd l<CR>

vnoremap <leader>t :Tabular<space>/

" Search for selected text, forwards or backwards.
vnoremap <silent> * :<C-U>
  \let old_reg=getreg('"')<Bar>let old_regtype=getregtype('"')<CR>
  \gvy/<C-R><C-R>=substitute(
  \escape(@", '/\.*$^~['), '\_s\+', '\\_s\\+', 'g')<CR><CR>
  \gV:call setreg('"', old_reg, old_regtype)<CR>
vnoremap <silent> # :<C-U>
  \let old_reg=getreg('"')<Bar>let old_regtype=getregtype('"')<CR>
  \gvy?<C-R><C-R>=substitute(
  \escape(@", '?\.*$^~['), '\_s\+', '\\_s\\+', 'g')<CR><CR>
  \gV:call setreg('"', old_reg, old_regtype)<CR>
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" 07. Plugins
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
"let g:miniBufExplMapWindowNavVim = 1        "minibufexplorer 
"let g:miniBufExplMapWindowNavArrows = 1     "minibufexplorer 
"let g:miniBufExplMapCTabSwitchBufs = 1      "minibufexplorer 
"let g:miniBufExplModSelTarget = 1           "minibufexplorer  

autocmd BufEnter * :syntax sync fromstart

""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
"""" Fugitive
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
"Clean fugitive buffers after checking git objects
autocmd BufReadPost fugitive://* set bufhidden=delete
"Add git branch name to status
set statusline=%<%f\ %h%m%r%{fugitive#statusline()}%=%-14.(%l,%c%V%)\ %P

nnoremap <Leader>ga :Git add %:p<CR><CR>
nnoremap <Leader>gs :Gstatus<CR>
nnoremap <Leader>gc :Gcommit -v -q<CR>
nnoremap <Leader>gt :Gcommit -v -q %:p<CR>
nnoremap <Leader>gd :Gdiff<CR>
nnoremap <Leader>gb :Gblame<CR>
nnoremap <Leader>gl :silent! Glog<CR>:bot copen<CR>
"nnoremap <Leader>gL :exe ':!cd ' . expand('%:p:h') . '; git la'<CR>
"nnoremap <Leader>gl :exe ':!cd ' . expand('%:p:h') . '; git las'<CR>
"nnoremap <Leader>gh :Silent Glog<CR>
"nnoremap <Leader>gH :Silent Glog<CR>:set nofoldenable<CR>
"Gread is dangerous in my opinion, will disable it for now
"nnoremap <Leader>gr :Gread<CR>
nnoremap <Leader>gw :Gwrite<CR>
nnoremap <Leader>gp :Ggrep<Space>
nnoremap <Leader>go :Git checkout<Space>
nnoremap <Leader>g- :Silent Git stash<CR>:e<CR>
nnoremap <Leader>g+ :Silent Git stash pop<CR>:e<CR>

""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
"""" FuzzyFinder
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
hi Pmenu ctermbg=red  
hi Pmenu ctermfg=black  

let g:fuf_modesDisable = []
let g:fuf_mrufile_maxItem = 1000
let g:fuf_mrucmd_maxItem = 400
let g:fuf_mrufile_exclude = '\v\~$|\.(o|d|bak|tsk|sw[po])$|^(\/\/|\\\\|\/mnt\/)'
"nnoremap <silent> <C-n>      :FufBuffer<CR>
nnoremap <silent> <Leader>n      :FufBuffer<CR>
"nnoremap <silent> <C-p>      :FufFileWithCurrentBufferDir<CR>
nnoremap <silent> <Leader>p      :FufFileWithCurrentBufferDir<CR>
"nnoremap <silent> <C-f><C-p> :FufFileWithFullCwd<CR>
nnoremap <silent> <Leader>f<Leader>p :FufFileWithFullCwd<CR>
"nnoremap <silent> <C-f>p     :FufFile<CR>
"nnoremap <silent> <C-f>p     :FufFile **/<CR>
nnoremap <silent> <Leader>fp     :FufFile **/<CR>
"nnoremap <silent> <C-f><C-d> :FufDirWithCurrentBufferDir<CR>
nnoremap <silent> <Leader>f<Leader>d :FufDirWithCurrentBufferDir<CR>
"nnoremap <silent> <C-f>d     :FufDirWithFullCwd<CR>
nnoremap <silent> <Leader>fd     :FufDirWithFullCwd<CR>
"nnoremap <silent> <C-f>D     :FufDir<CR>
nnoremap <silent> <Leader>fD     :FufDir<CR>
"nnoremap <silent> <C-j>      :FufMruFile<CR>
nnoremap <silent> <Leader>j      :FufMruFile<CR>
"nnoremap <silent> <C-k>      :FufMruCmd<CR>
nnoremap <silent> <Leader>k      :FufMruCmd<CR>
"nnoremap <silent> <C-b>      :FufBookmarkDir<CR>
nnoremap <silent> <Leader>b      :FufBookmarkDir<CR>
"nnoremap <silent> <C-f><C-t> :FufTag<CR>
nnoremap <silent> <Leader>f<Leader>t :FufTag<CR>
"nnoremap <silent> <C-f>t     :FufTag!<CR>
nnoremap <silent> <Leader>ft     :FufTag!<CR>
noremap  <silent> g]         :FufTagWithCursorWord!<CR>
"nnoremap <silent> <C-f><C-f> :FufTaggedFile<CR>
nnoremap <silent> <Leader>f<Leader>f :FufTaggedFile<CR>
"nnoremap <silent> <C-f><C-j> :FufJumpList<CR>
nnoremap <silent> <Leader>f<Leader>j :FufJumpList<CR>
"nnoremap <silent> <C-f><C-g> :FufChangeList<CR>
nnoremap <silent> <Leader>f<Leader>g :FufChangeList<CR>
"nnoremap <silent> <C-f><C-q> :FufQuickfix<CR>
nnoremap <silent> <Leader>f<Leader>q :FufQuickfix<CR>
"nnoremap <silent> <C-f><C-l> :FufLine<CR>
nnoremap <silent> <Leader>f<Leader>l :FufLine<CR>
"nnoremap <silent> <C-f><C-h> :FufHelp<CR>
nnoremap <silent> <Leader>f<Leader>h :FufHelp<CR>
"vnoremap <silent> <C-f><C-b> :FufAddBookmarkAsSelectedText<CR>
nnoremap <silent> <Leader>f<Leader>b :FufBookmarkFileAdd<CR>
"nnoremap <silent> <C-f><C-e> :FufEditInfo<CR>
nnoremap <silent> <Leader>f<Leader>e :FufEditDataFile<CR>
"nnoremap <silent> <C-f><C-r> :FufRenewCache<CR>
nnoremap <silent> <Leader>f<Leader>r :FufRenewCache<CR>


""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
"""" Cscope
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
set tags=tags;/                     "ctag
source ~/.vim/bundle/cscope_macros/cscope_macros.vim

"This will help Vim use cscope more efficiently
if has('cscope')
  set cscopetag cscopeverbose

  if has('quickfix')
    set cscopequickfix=s-,c-,d-,i-,t-,e-
  endif

  cnoreabbrev csa cs add
  cnoreabbrev csf cs find
  cnoreabbrev csk cs kill
  cnoreabbrev csr cs reset
  cnoreabbrev css cs show
  cnoreabbrev csh cs help

  command -nargs=0 Cscope cs add $VIMSRC/src/cscope.out $VIMSRC/src
endif


""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
"""" Upper/Lower case: mapping case conversion keys
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
if (&tildeop)
  nmap gcw guw~l
  nmap gcW guW~l
  nmap gciw guiw~l
  nmap gciW guiW~l
  nmap gcis guis~l
  nmap gc$ gu$~l
  nmap gcgc guu~l
  nmap gcc guu~l
  vmap gc gu~l
else
  nmap gcw guw~h
  nmap gcW guW~h
  nmap gciw guiw~h
  nmap gciW guiW~h
  nmap gcis guis~h
  nmap gc$ gu$~h
  nmap gcgc guu~h
  nmap gcc guu~h
  vmap gc gu~h
endif



""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
"""" Airline theme
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" let g:airline_theme='serene'
" let g:airline_theme='badwolf'
let g:airline_theme='murmur'

let g:snipMate = {}

""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
"""" Startify
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
    let g:startify_files_number        = 8
    let g:startify_session_persistence = 1
    let g:startify_session_autoload    = 1
    let g:startify_enable_special      = 0

    let g:startify_list_order = [
          \ ['   LRU:'],
          \ 'files',
          \ ['   Sessions:'],
          \ 'sessions',
          \ ['   Bookmarks:'],
          \ 'bookmarks',
          \ ]

    let g:startify_skiplist = [
                \ 'COMMIT_EDITMSG',
                \ $VIMRUNTIME .'/doc',
                \ 'bundle/.*/doc',
                \ '.vimgolf',
                \ ]

    let g:startify_bookmarks = [
                \ '~/.vimrc',
                \ '~/.tmux.conf',
                \ '~/.profile',
                \ ]

    let g:startify_custom_footer =
          \ ['', "   Vim is charityware. Please read ':help uganda'.", '']

    "let g:startify_custom_header =
    "      \ map(split(system('tips | cowsay -f apt'), '\n'), '"   ". v:val') + ['']
    let g:startify_custom_header1 = [
                \'                                                                                     bbbbbbbb                                                                        ',
                \'BBBBBBBBBBBBBBBBB   lllllll                                                          b::::::b                                                                        ',
                \'B::::::::::::::::B  l:::::l                                                          b::::::b                                                                        ',
                \'B::::::BBBBBB:::::B l:::::l                                                          b::::::b                                                                        ',
                \'BB:::::B     B:::::Bl:::::l                                                           b:::::b                                                                        ',
                \'  B::::B     B:::::B l::::l    ooooooooooo      ooooooooooo      mmmmmmm    mmmmmmm   b:::::bbbbbbbbb        eeeeeeeeeeee    rrrrr   rrrrrrrrr      ggggggggg   ggggg',
                \'  B::::B     B:::::B l::::l  oo:::::::::::oo  oo:::::::::::oo  mm:::::::m  m:::::::mm b::::::::::::::bb    ee::::::::::::ee  r::::rrr:::::::::r    g:::::::::ggg::::g',
                \'  B::::BBBBBB:::::B  l::::l o:::::::::::::::oo:::::::::::::::om::::::::::mm::::::::::mb::::::::::::::::b  e::::::eeeee:::::eer:::::::::::::::::r  g:::::::::::::::::g',
                \'  B:::::::::::::BB   l::::l o:::::ooooo:::::oo:::::ooooo:::::om::::::::::::::::::::::mb:::::bbbbb:::::::be::::::e     e:::::err::::::rrrrr::::::rg::::::ggggg::::::gg',
                \'  B::::BBBBBB:::::B  l::::l o::::o     o::::oo::::o     o::::om:::::mmm::::::mmm:::::mb:::::b    b::::::be:::::::eeeee::::::e r:::::r     r:::::rg:::::g     g:::::g ',
                \'  B::::B     B:::::B l::::l o::::o     o::::oo::::o     o::::om::::m   m::::m   m::::mb:::::b     b:::::be:::::::::::::::::e  r:::::r     rrrrrrrg:::::g     g:::::g ',
                \'  B::::B     B:::::B l::::l o::::o     o::::oo::::o     o::::om::::m   m::::m   m::::mb:::::b     b:::::be::::::eeeeeeeeeee   r:::::r            g:::::g     g:::::g ',
                \'  B::::B     B:::::B l::::l o::::o     o::::oo::::o     o::::om::::m   m::::m   m::::mb:::::b     b:::::be:::::::e            r:::::r            g::::::g    g:::::g ',
                \'BB:::::BBBBBB::::::Bl::::::lo:::::ooooo:::::oo:::::ooooo:::::om::::m   m::::m   m::::mb:::::bbbbbb::::::be::::::::e           r:::::r            g:::::::ggggg:::::g ',
                \'B:::::::::::::::::B l::::::lo:::::::::::::::oo:::::::::::::::om::::m   m::::m   m::::mb::::::::::::::::b  e::::::::eeeeeeee   r:::::r             g::::::::::::::::g ',
                \'B::::::::::::::::B  l::::::l oo:::::::::::oo  oo:::::::::::oo m::::m   m::::m   m::::mb:::::::::::::::b    ee:::::::::::::e   r:::::r              gg::::::::::::::g ',
                \'BBBBBBBBBBBBBBBBB   llllllll   ooooooooooo      ooooooooooo   mmmmmm   mmmmmm   mmmmmmbbbbbbbbbbbbbbbb       eeeeeeeeeeeeee   rrrrrrr                gggggggg::::::g ',
                \'                                                                                                                                                             g:::::g ',
                \'                                                                                                                                                 gggggg      g:::::g ',
                \'                                                                                                                                                 g:::::gg   gg:::::g ',
                \'                                                                                                                                                  g::::::ggg:::::::g ',
                \'                                                                                                                                                   gg:::::::::::::g  ',
                \'                                                                                                                                                     ggg::::::ggg    ',
                \'                                                                                                                                                        gggggg     ',
                \]

    let g:startify_custom_header = [
                \'',
                \'888888b.   888                                 888                                ',
                \'888  "88b  888                                 888                                ',
                \'888  .88P  888                                 888                                ',
                \'8888888K.  888  .d88b.   .d88b.  88888b.d88b.  88888b.   .d88b.  888d888  .d88b.  ',
                \'888  "Y88b 888 d88""88b d88""88b 888 "888 "88b 888 "88b d8P  Y8b 888P"   d88P"88b ',
                \'888    888 888 888  888 888  888 888  888  888 888  888 88888888 888     888  888 ',
                \'888   d88P 888 Y88..88P Y88..88P 888  888  888 888 d88P Y8b.     888     Y88b 888 ',
                \'8888888P"  888  "Y88P"   "Y88P"  888  888  888 88888P"   "Y8888  888      "Y88888 ',
                \'                                                                              888 ',
                \'                                                                         Y8b d88P ',
                \'                                                                          "Y88P"  ',
                \]
                                                                                                                                   
    hi StartifyBracket ctermfg=240
    hi StartifyNumber  ctermfg=215
    hi StartifyPath    ctermfg=245
    hi StartifySlash   ctermfg=240
    hi StartifySpecial ctermfg=240
    hi StartifyHeader  ctermfg=114
    hi StartifyFooter  ctermfg=240
    "hi StartifyFile    ctermfg=111
    "source /bbsrc/princeton/skunk/vim/cursor.vim

    " EasyGrep default search is in buffers
    let g:EasyGrepMode = 1


""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
""""SemanticHighlight
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
let s:semanticGUIColors = [ '#72d572', '#c5e1a5', '#e6ee9c', '#fff59d', '#ffe082', '#ffcc80', '#ffab91', '#bcaaa4', '#b0bec5', '#ffa726', '#ff8a65', '#f9bdbb', '#f9bdbb', '#f8bbd0', '#e1bee7', '#d1c4e9', '#ffe0b2', '#c5cae9', '#d0d9ff', '#b3e5fc', '#b2ebf2', '#b2dfdb', '#a3e9a4', '#dcedc8' , '#f0f4c3', '#ffb74d' ]

"Check http://img1.wikia.nocookie.net/__cb20110121055231/vim/images/1/16/Xterm-color-table.png
"let g:semanticTermColors = [28,1,2,3,4,5,6,7,25,9,10,34,12,13,14,15,16,125,124,19]
let g:semanticTermColors = [28,1,2,3,5,6,7,9,10,34,13,14,15,125,124,57,153,21]
nnoremap <Leader>s :SemanticHighlightToggle<cr>

""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
""""YouCompleteMe
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
let g:ycm_global_ycm_extra_conf = '/home/asadough/.vim/ycm_extra_conf.py'
"let g:syntastic_cpp_checkers = ['plink']
"let g:syntastic_cpp_include_dirs = ['bidea_utils/', 'bidea_utils/shared_xsds', 'btmsgsvc/src', '/bbsrc']
