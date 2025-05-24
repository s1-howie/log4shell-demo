
# Stage 1: Compile the servlet and package as WAR
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app
COPY VulnWebApp.java .
COPY log4j-core-2.14.1.jar .
COPY log4j-api-2.14.1.jar .
COPY servlet-api.jar .
COPY web.xml .

RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:servlet-api.jar" VulnWebApp.java -d WEB-INF/classes && \
    mkdir -p WEB-INF && \
    cp web.xml WEB-INF/web.xml && \
    jar cvf ROOT.war -C WEB-INF .

# Stage 2: Deploy WAR into Tomcat
FROM tomcat:9.0-jdk17-openjdk
COPY --from=builder /app/ROOT.war /usr/local/tomcat/webapps/ROOT.war
