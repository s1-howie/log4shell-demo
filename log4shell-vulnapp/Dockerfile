FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app
COPY VulnWebApp.java .
COPY log4j-api-2.14.1.jar .
COPY log4j-core-2.14.1.jar .
COPY web.xml .

# Compile the servlet
RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-api-2.14.1.jar:log4j-core-2.14.1.jar:." VulnWebApp.java -d WEB-INF/classes && \
    mkdir -p WEB-INF && \
    cp web.xml WEB-INF/web.xml && \
    jar cvf ROOT.war -C WEB-INF .

FROM tomcat:9.0-jdk17-openjdk

# Deploy the WAR as ROOT
COPY --from=builder /app/ROOT.war /usr/local/tomcat/webapps/ROOT.war
