FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app
COPY VulnWebApp.java .
COPY web.xml .
COPY log4j-api-2.14.1.jar .
COPY log4j-core-2.14.1.jar .
COPY servlet-api.jar .

RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-api-2.14.1.jar:log4j-core-2.14.1.jar:servlet-api.jar" VulnWebApp.java -d WEB-INF/classes && \
    cp web.xml WEB-INF/ && \
    jar cvf ROOT.war -C WEB-INF .

FROM tomcat:9.0-jdk17-openjdk
COPY --from=builder /app/ROOT.war /usr/local/tomcat/webapps/