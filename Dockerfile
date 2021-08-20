FROM java:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8090
COPY ${JAR_FILE} MFPSaleMS.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/MFPSaleMS.jar"]