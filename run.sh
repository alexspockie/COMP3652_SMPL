jflex Lexer.jflex
cup -parser ArithParser Parser.cup
javac -classpath ".:./lib3652.jar:/usr/share/java/cup.jar" *.java
java -classpath ".:./lib3652.jar:/usr/share/java/cup.jar" Main -
