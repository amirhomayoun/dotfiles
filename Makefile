.FORCE:
.PHONY = clean realclean start svc build buildc rebuild cleanall

REALCLEAN = rm -rf 00* tmp.* *.tmp *.o *.tsk *.a *.so *.log.* *.d *.dd ported.* *.tsk.mapfile
SVC = myservice
UPPER_SVC = $(call uc,$(SVC))


TRACE = TRACE
DEBUG = DEBUG
INFO = INFO
WARN = WARN
ERROR = ERROR
SRCH :=

SVC_NO = 12345
GITLOG = $(shell git log -1 --pretty=%B)
THISISME = myUsername
whoami = $(shell whoami)
YEAR= $(shell date +%Y)

lc = $(subst A,a,$(subst B,b,$(subst C,c,$(subst D,d,$(subst E,e,$(subst F,f,$(subst G,g,$(subst H,h,$(subst I,i,$(subst J,j,$(subst K,k,$(subst L,l,$(subst M,m,$(subst N,n,$(subst O,o,$(subst P,p,$(subst Q,q,$(subst R,r,$(subst S,s,$(subst T,t,$(subst U,u,$(subst V,v,$(subst W,w,$(subst X,x,$(subst Y,y,$(subst Z,z,$1))))))))))))))))))))))))))

uc = $(subst a,A,$(subst b,B,$(subst c,C,$(subst d,D,$(subst e,E,$(subst f,F,$(subst g,G,$(subst h,H,$(subst i,I,$(subst j,J,$(subst k,K,$(subst l,L,$(subst m,M,$(subst n,N,$(subst o,O,$(subst p,P,$(subst q,Q,$(subst r,R,$(subst s,S,$(subst t,T,$(subst u,U,$(subst v,V,$(subst w,W,$(subst x,X,$(subst y,Y,$(subst z,Z,$1))))))))))))))))))))))))))

NOT = not() { \
    echo "" >~/.tmuxAlertStop; \
    echo "Start: [`date +%H:%M`]">~/.tmuxAlertStart;\
}\

TON = ton() { \
    echo "Stop: [`date +%H:%M`]">~/.tmuxAlertStop;\
}

all: help

clean:
	    cd svc/; chmod -w *.tsk; plink $(SVC).mk clean

help:
	    @echo make [ clean realclean ] start svc build buildc rebuild dbup dbclean cleanall test runtest 

remove: 
		cd svc/; rm -r 00*

realclean: remove clean
	    cd msg/; $(REALCLEAN)
	    cd src/; $(REALCLEAN)
	    cd db/; $(REALCLEAN)
	    cd svc/; chmod +w *.tsk; $(REALCLEAN)
	    cd test/; $(MAKE) clean; $(REALCLEAN)

tail:
ifndef L
	$(eval SRCH :=\<$(ERROR)\>\|\<$(WARN)\>\|\<$(INFO)\>\|\<$(DEBUG)\>\|\<$(TRACE)\>)
else
ifeq ($(L),ERROR)
	$(eval SRCH :=\<$(ERROR)\>)
else
ifeq ($(L),WARN)
	$(eval SRCH :=\<$(WARN)\>)
else
ifeq ($(L),INFO)
	$(eval SRCH :=\<$(INFO)\>)
else
ifeq ($(L),DEBUG)
	$(eval SRCH :=\<$(DEBUG)\>)
else
ifeq ($(L),TRACE)
	$(eval SRCH :=\<$(TRACE)\>)
else
ifeq ($(L),WARN+)
	$(eval SRCH :=\<$(ERROR)\>\|\<$(WARN)\>)
else
ifeq ($(L),INFO+)
	$(eval SRCH :=\<$(ERROR)\>\|\<$(WARN)\>\|\<$(INFO)\>)
else
ifeq ($(L),DEBUG+)
	$(eval SRCH :=\<$(ERROR)\>\|\<$(WARN)\>\|\<$(INFO)\>\|\<$(DEBUG)\>)
else
	$(eval SRCH :=\<$(ERROR)\>\|\<$(WARN)\>\|\<$(INFO)\>\|\<$(DEBUG)\>\|\<$(TRACE)\>)
endif
endif
endif
endif
endif
endif
endif
endif
endif

	    cd logs; tail -f $(SVC).log.$(YEAR) |stdbuf -i0 -o0 -e0 grep --line-buffer $(UPPER_SVC) | GREP_COLORS='ms=01;36:mc=01;31:sl=:cx=:fn=35:ln=32:bn=32:se=36' stdbuf -i0 -o0 -e0 grep -i --line-buffer --color=always '${SRCH}'| GREP_COLORS='ms=01;33' grep --color=always -P "(\w*)(\.[cphf]*\:[0-9]*)"
	    #cd logs; tail -f $(SVC).log.$(YEAR) | GREP_COLORS='ms=01;36:mc=01;31:sl=:cx=:fn=35:ln=32:bn=32:se=36' grep -i --color=always '${SRCH}'| GREP_COLORS='ms=01;32' grep --color=always -P "(\w*)(\.[cphf]*\:[0-9]*)"
	    #cd logs/; tail -f $(SVC).log.2015 #Haha, this will break in about 6 months
	    #cd logs/; tail -f $(SVC).log.$(YEAR) | grep -i --color=always '$(UPPER_SVC)\|$(UPPER_SHARED))' #Haha, this will break in about 6 months

bpk:
ifndef MINOR
	@echo "Error: Provide the Minor version: MINOR=1"
else
ifndef COMMENT
	    cd svc/; 
else
	    cd svc/; 
endif
endif

start:
	    cd svc/; ./$(SVC).tsk $(SVC).cfg.dev

dbx:
	    cd svc/; dbx ./$(SVC).tsk -c "run $(SVC).cfg" 

build: .FORCE
ifeq ($(whoami),$(THISISME))
		@$(NOT); not
	  cd svc/; link | raffaello --file=/home/.raffaelo/cpp.cfg
		@$(TON); ton
else
		cd svc/; link
endif

svc: build wait start

wait: 
	sleep 2;

buildc: clean build start

rebuild: clean build start
