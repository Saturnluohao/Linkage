FROM openjdk:13-alpine
RUN mkdir /home/linkage
RUN mkdir /home/linkage/log
ADD webserver/target/webserver-0.0.1-SNAPSHOT.jar /home/linkage/
ENTRYPOINT [ "java", "-jar", "/home/linkage/webserver-0.0.1-SNAPSHOT.jar"]