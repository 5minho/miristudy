#!/bin/bash

TEMP_DIR=./.temp
WORKSPACE_DIR=$TEMP_DIR/workspace

ARTIFACT_PATTERN=build/libs/*.jar

echo 'build'
./gradlew clean build -x test

echo 'clean workspace'
rm -rf $TEMP_DIR
mkdir -p $WORKSPACE_DIR

echo 'copy artifact'
cp ./$ARTIFACT_PATTERN $WORKSPACE_DIR/app.jar

echo 'copy beanstalk extension'
cp -r ./beanstalk/. $WORKSPACE_DIR

echo 'zip application archive'

cd $WORKSPACE_DIR
zip -r ../miristudy-api.zip .

