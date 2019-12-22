# Antlr-Calculator

Antlr Calculator visitor implemention using Java.

**To run the calculator**

-Download antlr4.7.2 https://www.antlr.org/download.html

-Rename the jar file as antlr.jar

-Download the .java and .g4 files and store everything in the same folder.

**Run the following commands in the folder to run the calculator**

java -jar antlr.jar Calculator.g4 -visitor

javac -cp .:antlr.jar *.java

java -cp .:antlr.jar Run

**Calculator Features**

- All basic arithematic operations.

- Variable Storage

- Supports scienfic and decimal numbers

- Turnary Operations

- List Storage with size functions and 0-based-index access to elements.


