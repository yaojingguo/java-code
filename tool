#!/bin/bash

ant compile
if [ $? -eq 0 ]; then
  java -cp build/src concurrency.$1
fi
