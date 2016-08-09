#!/usr/bin/env bash

mvn clean package

rm -rf output
mkdir output

cp client/target/*.jar output/
cp server/target/*.jar output/