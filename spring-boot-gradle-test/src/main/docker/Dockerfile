FROM java:8
VOLUME /tmp
ADD build/libs/spring-boot-gradle-*.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8002
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
