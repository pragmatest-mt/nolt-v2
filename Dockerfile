FROM maven:3.6.3-jdk-11

WORKDIR /app
ENTRYPOINT ["/app/entrypoint.sh"]