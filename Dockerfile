FROM openjdk:12

VOLUME /tmp

ADD ./target/st-microservice-oauth-0.0.1-SNAPSHOT.jar st-microservice-oauth.jar

EXPOSE 8080

ENTRYPOINT java -jar /st-microservice-oauth.jar