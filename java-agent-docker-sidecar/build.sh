#!/bin/bash

AGENT_VERSION=$1
if [ ! $1 ]; then
	echo "AGENT VERSION NOT SUPPLIED, USING LATEST"
	AGENT_VERSION=latest
fi
docker build --build-arg AGENT_VERSION=${AGENT_VERSION} --no-cache -t splunk-otel-java-repo:$AGENT_VERSION -f Dockerfile .
