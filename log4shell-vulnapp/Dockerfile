FROM eclipse-temurin:8-jdk AS build

WORKDIR /build

# Download dependencies
ADD https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-core/2.14.1/log4j-core-2.14.1.jar .
ADD https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.14.1/log4j-api-2.14.1.jar .
ADD https://repo1.maven.org/maven2/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar .

# Copy source
COPY VulnWebApp.java .
COPY web.xml ./WEB-INF/

# Compile
RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:javax.servlet-api-3.1.0.jar" VulnWebApp.java -d WEB-INF/classes

# Package WAR
RUN jar -cvf ROOT.war WEB-INF

# ---

FROM jetty:9.4-jdk8

# Add Log4j to Jetty's lib/ext so it's on the runtime classpath
COPY --from=build /build/log4j-core-2.14.1.jar /opt/jetty/lib/ext/
COPY --from=build /build/log4j-api-2.14.1.jar /opt/jetty/lib/ext/

# Deploy WAR into Jetty
COPY --from=build /build/ROOT.war /var/lib/jetty/webapps/ROOT.war
