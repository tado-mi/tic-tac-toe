# variable for java compiler
JC = javac
J = java
D = -d
CD = cd

# damage control
.SUFFIXES: .java .class

# target for creating .class from .java in format:
#	.original_extention.target_extention:
#		rule
.java.class:
	$(JC) $*.java $(D) bin

# macro for each java source file
CLASSES = \
	util/Position3D.java \
	util/State.java \
	util/State3D.java \
	TicTacToe2D.java \
	TicTacToe3D.java \
	Demo.java

# default target definition
default: classes

classes: $(CLASSES:.java=.class)

part1:
	$(CD) bin && $(J) Demo 1

part2:
	$(CD) bin && $(J) Demo 2

clean:
	$(RM) *.class
