#!/bin/sh
here="`dirname \"$0\"`"
echo "cd-ing to $here"
cd "$here" || exit 1

# main_program="TestGraphic"
main_program="GraphicsEditor"
java -cp bin "$main_program"
