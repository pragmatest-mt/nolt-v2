FROM maven:3.6.3-jdk-11-slim

COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN apt-get update && apt-get install dos2unix && dos2unix /usr/local/bin/entrypoint.sh && chmod +x /usr/local/bin/entrypoint.sh

#Start application
COPY . /usr
WORKDIR /usr
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]