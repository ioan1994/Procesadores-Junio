CLASSPATH = .

init:
	javac java_cup/runtime/*.java
	javac java_cup/*.java


all:
	cup cupBD.cup
	jlex lexBD.lex
	javac sym.java parser.java lexBD.lex.java
run:
	java -classpath $(CLASSPATH) parser

cleanall:
	-rm *.class
	-rm Yylex.java
	-rm parser.java
	-rm sym.java
	-rm java_cup/*.class
	-rm java_cup/runtime/*.class
	-rm lexBD.lex.java
