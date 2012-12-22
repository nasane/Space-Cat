JFLAGS = 
JC = javac

sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(classes)

clean:
	rm -f *.class

default: all

%.class : %.java 
	$(JC) $(JFLAGS) $<
