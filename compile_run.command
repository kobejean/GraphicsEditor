#!/bin/sh

# GRAPHICS EDITOR

here="`dirname \"$0\"`"
echo "cd-ing to $here"
cd "$here" || exit 1
javac -encoding ISO-8859-1 @compile_list.txt -d bin
# run
chmod 755 run.command
open -a "Terminal" run.command
