FROM eclipse-temurin:8-jdk AS builder

WORKDIR /app

# Download dependencies
ADD https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-core/2.14.1/log4j-core-2.14.1.jar .
ADD https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.14.1/log4j-api-2.14.1.jar .
ADD https://repo1.maven.org/maven2/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar .

# Add source files
COPY VulnWebApp.java .
COPY web.xml ./WEB-INF/web.xml

# Compile servlet
RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:javax.servlet-api-3.1.0.jar" \
    VulnWebApp.java -d WEB-INF/classes && \
    jar cvf ROOT.war -C WEB-INF .

# ------------------

FROM jetty:9.4-jdk8

# Add log4j JARs to Jetty lib
COPY --from=builder /app/log4j-core-2.14.1.jar /opt/jetty/lib/ext/
COPY --from=builder /app/log4j-api-2.14.1.jar /opt/jetty/lib/ext/

# Deploy WAR file
COPY --from=builder /app/ROOT.war /var/lib/jetty/webapps/ROOT.war
