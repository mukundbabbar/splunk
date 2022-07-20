FROM alpine:latest

RUN mkdir -p /tmp/agent
ARG AGENT_VERSION
RUN wget "https://github.com/signalfx/splunk-otel-java/releases/$AGENT_VERSION/download/splunk-otel-javaagent.jar" -O  splunk-otel-javaagent.jar
RUN mv splunk-otel-javaagent.jar /tmp/agent/
RUN echo $(ls /tmp/agent)


