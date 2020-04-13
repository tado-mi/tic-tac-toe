# variable for java compiler
JC = javac
J = java

# damage control
.SUFFIXES: .java .class

# target for creating .class from .java in format:
#	.original_extention.target_extention:
#		rule
.java.class:
	$(JC) $*.java
	
# macro for each java source file
CLASSES = \
	Position3D.java \
	State.java \
	State3D.java \
	TicTacToe2D.java \
	TicTacToe3D.java \
	Demo.java
	
# default target definition
default: classes

classes: $(CLASSES:.java=.class)

part1:
	$(J) Demo 1

part2:
	$(J) Demo 2

clean:
	$(RM) *.class
	
