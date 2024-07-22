#!/usr/bin/env bash

MAINCLASS=com.example.DemoApplication
VERSION=0.0.1-SNAPSHOT

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

rm -rf target
mkdir -p target/native-image

echo "Building native image $ARTIFACT with Maven"
gradle nativeCompile

if [[ -f "build/native/nativeCompile/azure-native-spring-function" ]]
then
  printf "${GREEN}SUCCESS${NC}\n"
  mv build/native/nativeCompile/azure-native-spring-function build/function/spring-native-image
  exit 0
else
  printf "${RED}FAILURE${NC}: an error occurred when compiling the native-image.\n"
  exit 1
fi

