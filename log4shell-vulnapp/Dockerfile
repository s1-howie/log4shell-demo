# Builder Stage
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app

# Copy source files
COPY VulnWebApp.java .
COPY log4j-api-2.14.1.jar .
COPY log4j-core-2.14.1.jar .
COPY web.xml .

# Download servlet-api for compilation
RUN curl -O https://repo1.maven.org/maven2/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar

# Compile the servlet
RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:javax.servlet-api-3.1.0.jar" VulnWebApp.java -d WEB-INF/classes && \
    mkdir -p WEB-INF && \
    mv web.xml WEB-INF/web.xml && \
    jar cvf ROOT.war -C WEB-INF .

# Runtime Stage
FROM tomcat:9.0-jdk17-openjdk
COPY --from=builder /app/ROOT.war /usr/local/tomcat/webapps/
