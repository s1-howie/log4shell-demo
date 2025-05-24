
# Stage 1: Build
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app

COPY VulnWebApp.java .
COPY log4j-api-2.14.1.jar .
COPY log4j-core-2.14.1.jar .
COPY servlet-api.jar .
COPY web.xml .

RUN mkdir -p build/WEB-INF/classes build/WEB-INF/lib \
 && javac -cp "log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:servlet-api.jar" VulnWebApp.java -d build/WEB-INF/classes \
 && cp log4j-core-2.14.1.jar build/WEB-INF/lib/ \
 && cp log4j-api-2.14.1.jar build/WEB-INF/lib/ \
 && cp web.xml build/WEB-INF/web.xml \
 && cd build && jar -cvf ROOT.war .

# Stage 2: Deploy to Tomcat
FROM tomcat:9.0-jdk17-openjdk
COPY --from=builder /app/build/ROOT.war /usr/local/tomcat/webapps/ROOT.war
