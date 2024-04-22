#!/bin/bash

# Define JDK version
JDK_VERSION=21

# Download and install JDK
wget -qO- "https://download.java.net/java/GA/jdk${JDK_VERSION}/openjdk-${JDK_VERSION}_linux-x64_bin.tar.gz" | tar xvz -C /usr/local/

# Set JAVA_HOME and PATH
export JAVA_HOME="/usr/local/jdk-${JDK_VERSION}"
export PATH="${JAVA_HOME}/bin:${PATH}"

# Execute your build command (e.g., Gradle)
./gradlew clean build
