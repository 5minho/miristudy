#!/bin/bash

PROJECT_DIR=$(pwd)

TEMP_DIR=$PROJECT_DIR/.temp
WORKSPACE_DIR=$TEMP_DIR/workspace

ARTIFACT_PATTERN=build/libs/*.jar
echo $TEMP_DIR
echo 'clean workspace'
rm -rf $TEMP_DIR
mkdir -p $WORKSPACE_DIR

echo 'copy artifact'
cp $PROJECT_DIR/$ARTIFACT_PATTERN $WORKSPACE_DIR/app.jar

echo 'copy beanstalk extension'
cp -r $PROJECT_DIR/beanstalk/. $WORKSPACE_DIR

echo 'zip application archive'
cd $WORKSPACE_DIR
zip -r ../miristudy-api.zip .

