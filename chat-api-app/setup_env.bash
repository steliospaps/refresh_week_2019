#!/bin/bash -c 'echo please source this file >&2; exit 1'

~/.jabba/bin/jabba install openjdk@1.11.0
export JAVA_HOME=$( ~/.jabba/bin/jabba which openjdk@1.11.0)
export PATH="$JAVA_HOME/bin:$PATH"
