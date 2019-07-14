#!/bin/bash
# Create a sample test directory
mkdir test && cd test && echo "cloning the repo..."
# clone the repo 
git clone https://github.com/bgpalan/code-challenge.git
# Build the project 
cd code-challenge && mvn clean install && cd target
# Start the migration service jar 
java -jar migration-service.jar

