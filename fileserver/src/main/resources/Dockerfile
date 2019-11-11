FROM openjdk:13-alpine
RUN mkdir /home/linkage
RUN mkdir /home/saturn
RUN mkdir /home/saturn/data
ADD fileserver/target/fileserver-0.0.1-SNAPSHOT.jar /home/linkage/
ENTRYPOINT [ "java", "-jar", "/home/linkage/fileserver-0.0.1-SNAPSHOT.jar"]