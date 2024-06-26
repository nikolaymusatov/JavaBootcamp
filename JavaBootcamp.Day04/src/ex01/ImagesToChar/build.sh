#!/bin/bash

javac -d target src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java
cp -r resourses target
cd target && jar -c -f images-to-char-printer.jar -m ../src/MANIFEST.MF ImagesToChar/src/java/edu/school21/printer/app/Program.class ImagesToChar/src/java/edu/school21/printer/logic/Converter.class resourses


