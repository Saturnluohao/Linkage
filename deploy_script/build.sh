#!/bin/expect

docker build -f fileserver/src/main/resources/Dockerfile -t fileserver:latest .
docker build -f webserver/src/main/resources/Dockerfile -t webserver:latest .
sudo apt-get install -y expect
expect deploy_script/login.sh
docker tag fileserver:latest saturnluo/fileserver:latest
docker tag webserver:latest saturnluo/webserver:latest
docker push saturnluo/fileserver:latest
docker push saturnluo/webserver:latest
expect deploy_script/copyFile.sh