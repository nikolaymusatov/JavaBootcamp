#!/bin/bash

javac -cp lib/\* -d target src/java/edu/school21/printer/app/*.java src/java/edu/school21/printer/logic/*.java
cp -r resourses target
cd target && jar -c -f images-to-char-printer.jar -m ../src/MANIFEST.MF ImagesToChar/src/java/edu/school21/printer/app/*.class ImagesToChar/src/java/edu/school21/printer/logic/*.class resourses


