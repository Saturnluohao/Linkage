FROM openjdk:13-alpine
RUN mkdir /home/linkage
RUN mkdir /home/saturn
RUN mkdir /home/saturn/data
RUN mkdir /home/saturn/data/img
RUN mkdir /home/saturn/data/video
RUN mkdir /home/saturn/data/icon
RUN mkdir /home/saturn/data/global_icon
ADD fileserver/target/fileserver-0.0.1-SNAPSHOT.jar /home/linkage/
ENTRYPOINT [ "java", "-jar", "/home/linkage/fileserver-0.0.1-SNAPSHOT.jar"]