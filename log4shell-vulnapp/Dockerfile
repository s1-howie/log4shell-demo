# ---- Stage 1: Build WAR file ----
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app

# Download required libraries
RUN curl -O https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.14.1/log4j-api-2.14.1.jar && \
    curl -O https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-core/2.14.1/log4j-core-2.14.1.jar && \
    curl -O https://repo1.maven.org/maven2/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar

# Copy Java source and web.xml
COPY VulnWebApp.java .
COPY web.xml .

# Compile servlet and package into WAR
RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:javax.servlet-api-3.1.0.jar" VulnWebApp.java -d WEB-INF/classes && \
    cp web.xml WEB-INF/web.xml && \
    jar cvf ROOT.war -C WEB-INF .

# ---- Stage 2: Run Jetty with the WAR ----
FROM jetty:11-jdk17
COPY --from=builder /app/ROOT.war /var/lib/jetty/webapps/ROOT.war
