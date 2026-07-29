#!/bin/bash

cd code

# Create the zip file
rm -f ../imageLegendsFunction.zip
zip -r ../imageLegendsFunction.zip *.js package.json

cd ..
