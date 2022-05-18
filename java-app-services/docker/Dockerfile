FROM openjdk:8-jre-slim

RUN apt-get update && \
    apt-get install -y procps binutils vim curl && \
    apt-get clean

COPY startup.sh /startup.sh

RUN chmod +x /startup.sh

#RUN chmod +w /etc/resolv.conf


ADD java-app-services.jar java-app-services.jar

ENTRYPOINT ["/bin/bash", "/startup.sh"]
