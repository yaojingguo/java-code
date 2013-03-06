#!/bin/bash

ant compile
java -cp build concurrency.$1
