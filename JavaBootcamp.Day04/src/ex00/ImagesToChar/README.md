## Getting Started

Welcome. Here is a guideline to help you get started with ImagesToChar project.

## Folder Structure

The workspace contains one folder by default, where:
- `src`: the folder to maintain sources

Meanwhile, the compiled output files will be generated in the `target` folder by default.

## Compiling

To manually compile the project use command `javac -d target src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java` in ImagesToChar main folder.

## Executing

To execute program after compilation change your current directory to `target` and the execute
`java ImagesToChar.src.java.edu.school21.printer.app.Program [PATH] [BLANK] [FILLED]`, where:
- PATH - is a path to 2-colored BMP-file,
- BLANK - is a symbol to represent pixels without color component,
- FILLED - is a symbol to represent colored pixels.
