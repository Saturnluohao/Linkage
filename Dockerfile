FROM openjdk:8-jdk-alpine
RUN mkdir /home/linkage
ADD fileserver/target/fileserver-0.0.1-SNAPSHOT.jar /home/linkage/
ENTRYPOINT [ "java", "-jar", "/home/linkage/fileserver-0.0.1-SNAPSHOT.jar"]