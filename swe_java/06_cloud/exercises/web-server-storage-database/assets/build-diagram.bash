#!/bin/bash

HERE=`pwd`
DIR=`dirname $0`
echo $DIR
cd $DIR
pwd
python3 diagram.py
cd $HERE

