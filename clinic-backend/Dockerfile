#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","pl.moscicki.clinicbackend.ClinicBackendApplication"]

#FROM openjdk:10-jre-slim
#COPY ./target/hola-docker-1.0.0-SNAPSHOT.jar /usr/src/hola/
#WORKDIR /usr/src/hola
#EXPOSE 8080
#CMD ["java", "-jar", "hola-docker-1.0.0-SNAPSHOT.jar"]

FROM maven:3.5-jdk-8