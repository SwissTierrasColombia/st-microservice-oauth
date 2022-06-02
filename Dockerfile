FROM openjdk:11

ARG XMX=1024m
ARG PROFILE=production
ARG CLOUD_CONFIG
ARG NEW_RELIC_ENVIRONMENT

ENV XMX=$XMX
ENV PROFILE=$PROFILE
ENV CLOUD_CONFIG=$CLOUD_CONFIG
ENV NEW_RELIC_ENVIRONMENT=$NEW_RELIC_ENVIRONMENT

VOLUME /tmp

ADD ./target/st-microservice-oauth-1.2.0.jar st-microservice-oauth.jar
ADD ./target/newrelic.jar newrelic.jar
ADD ./newrelic.yml newrelic.yml

EXPOSE 8080

ENTRYPOINT java -javaagent:/newrelic.jar -Dnewrelic.environment=$NEW_RELIC_ENVIRONMENT -Xmx$XMX -jar /st-microservice-oauth.jar --spring.profiles.active=$PROFILE --spring.cloud.config.uri=$CLOUD_CONFIG