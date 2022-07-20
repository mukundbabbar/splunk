
# Instrumenting java apps using sidecar to docker containers

## Pending tests

This workshop instruments java applications without changing the application image by injecting the java agent through sidecar container. Same logic can be applied to other instrumentations - Nodejs, Python or .NET. This will help keep both the agent and app independent eg. dev teams do not have to rebuild the app image if they have to upgrade agent version.


Requirements
- Docker (Logic can be applied on ECS as well (configuration not included in this workshop))
- Splunk Observability Cloud access and values for $REALM and $ACCESS_TOKEN

## Step 0 - Prep

Download java-agent-docker-sidecar directory - https://download-directory.github.io/?url=https%3A%2F%2Fgithub.com%2Fmukundbabbar%2Fsplunk%2Ftree%2Fmain%2Fjava-agent-docker-sidecar

Lets start from java-agent-docker-sidecar directory

```
cd $path/java-agent-docker-sidecar
```

## Step 1 - Build Sidecar image with java agent

If $version is not specified, it downloads the latest splunk-otel-java agent

```
./build $version
```

<img width="1098" alt="Screen Shot 2022-07-21 at 12 03 53 am" src="https://user-images.githubusercontent.com/5012739/180002642-ce348b9f-c2fe-42a4-886e-ae69067dacba.png">

Verify image 

<img width="528" alt="Screen Shot 2022-07-21 at 12 04 13 am" src="https://user-images.githubusercontent.com/5012739/180002661-c2bfa99b-f4da-49b3-973b-ee74b1087739.png">

## Step 2 - Instrumentation

We will be using java-app-services image which is not instrumented and uses JAVA_OPTS in startup.sh file. There are multiple ways to inject the agent through sidercar. 
- We can use docker compose to provide dependency on shared volume
- OR use startup script in the app to copy the file to local container
- OR use catalina.sh or wrapper.conf or webenv.sh file to access shared volume

### docker compose example

Update docker-compose.yml file with REALM and ACCESS_KEY

Start the app

```
cd java-agent-docker-sidecar 
docker compose up
```

## Step 3 - Generate Load

Run following command to generate load

```
docker exec web-frontend curl localhost:8080
docker exec web-frontend curl localhost:8080/WebFrontEnd
```

The app should be started with the agent. Check Splunk Observability Cloud service view to confirm 






