FROM maven:3.6.0-jdk-11-slim
# Update apt and install git locally 
RUN apt-get update
RUN apt-get -yq install git
# Add the bootstrap file and 
ADD bootstrap.sh /
ENTRYPOINT ["/bin/bash", "/bootstrap.sh"]