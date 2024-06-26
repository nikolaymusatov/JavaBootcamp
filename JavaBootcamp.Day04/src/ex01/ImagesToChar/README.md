## Getting Started

Welcome. Here is a guideline to help you get started with ImagesToChar project.

## Folder Structure

The workspace contains one folder by default, where:
- `src`: the folder to maintain sources

Meanwhile, the compiled output files will be generated in the `target` folder by default.

## Compiling

Сompilation is done by running `build.sh` file. Make sure to give a permission for execution to it by running `sudo chmod +x build.sh`.
Then to compile the project use command `./build.sh` in ImagesToChar main folder. `build.sh` also provides instructions to create a JAR-file,
which u can find in `target` folder after compiling. 

## Executing

To execute program after compilation change your current directory to `target` and the execute
`java ImagesToChar.src.java.edu.school21.printer.app.Program [BLANK] [FILLED]`, where:
- BLANK - is a symbol to represent pixels without color component,
- FILLED - is a symbol to represent colored pixels.

It is also possible to execute the program using JAR-file. To do so u can use `java -jar images-to-char-printer.jar [BLANK] [FILLED]`
command in `target` folder.
