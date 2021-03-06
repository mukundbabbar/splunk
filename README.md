# Instrumenting applications on kubernetes using init containers

This workshop will focus on how we can instrument application by injecting the java agent into the applicaiton container using init containers and keeping the app code and agent independent of each other. We will be using un-instrumented application (included) and then injecing the java agent without changing the application code. Same logic can be applied to other instrumentations - Nodejs, Python or .NET

Init container is used to download the splunk java agent and using it in the main container using volume mount. Other way is to upload agent docker image and then copying the binary to tmp volume which can then be used by the app. Configuration of JAVA_OPTS exist in the configmap.

![K8s instrument Java (2)](https://user-images.githubusercontent.com/5012739/177473597-584bdd37-d8fb-4d40-a618-a0bbdebdfe35.jpeg)


Requirements
- Kubectl and access to any K8s variant
- Splunk Observability Cloud access and values for $REALM and $ACCESS_TOKEN

## Step 0 - 

Download java-app-specs directory - https://download-directory.github.io/?url=https%3A%2F%2Fgithub.com%2Fmukundbabbar%2Fsplunk%2Ftree%2Fmain%2Fjava-app-specs

Lets start from java-app-specs directory

```
cd $path/java-app-specs
```

## Step 1 - Deploy un-instrumented services

```
kubectl create ns apps
kubectl create -n apps -f sa.yaml
kubectl create -n apps -f appspecs
```

Check status of services

```
kubectl -n apps get pods
```

<img width="486" alt="Screen Shot 2022-07-06 at 1 53 23 pm" src="https://user-images.githubusercontent.com/5012739/177464677-4e5c833c-bf56-40bc-ba15-9c3351e483f0.png">

## Step 2 - Install OTEL collector

We are using HELM chart to install Splunk otel collector

```
export ACCESS_TOKEN=**<ACCESS TOKEN>**
export REALM=**<REALM>**
export CLUSTER_NAME=**<CLUSTER NAME>**
export ENV=**ENV_NAME**
helm repo add splunk-otel-collector-chart https://signalfx.github.io/splunk-otel-collector-chart && helm repo update
helm install splunk-otel-collector \
    --set="splunkObservability.realm=$REALM" \
    --set="splunkObservability.accessToken=$ACCESS_TOKEN" \
    --set="clusterName=$CLUSTER_NAME" \
    --set="splunkObservability.logsEnabled=true" \
    --set="environment=$ENV" \
    splunk-otel-collector-chart/splunk-otel-collector \
    -f otel-collector.yaml   
```

Check status of otel collector

```
kubectl get pods
kubectl logs -l app=splunk-otel-collector -f --container otel-collector
```

Validate Splunk Observability Cloud K8s navigator  

<img width="751" alt="Screen Shot 2022-07-06 at 2 31 17 pm" src="https://user-images.githubusercontent.com/5012739/177468982-b06a16f9-a54c-418e-9aa7-661a00063dec.png">

## Step 3 - Instrument services with Splunk otel java agent

```
kubectl create -f agent-config.yaml -n apps
kubectl -n apps apply -f appspecs-instr
```

Difference between appspecs and appspecs-instr is that latter has updated specs with init containers. Once updated configuration is applied, the pods will restart with the agent. 

## Step 4 - Generate load 

Using CronJob to run curl commands every 3 minutes

```
kubectl -n apps apply -f load-gen.yaml
```

Validate Splunk Observability Cloud APM UI. There will be a number of services as shown below

<img width="842" alt="Screen Shot 2022-07-06 at 10 07 19 pm" src="https://user-images.githubusercontent.com/5012739/177546499-dca056d8-dad8-4141-82b8-42b80dc274ee.png">

## Clean Env

Delete the apps and remove otel collector

```
kubectl delete ns apps
helm uninstall splunk-otel-collector
kubectl delete cm agent-config -n apps
```

