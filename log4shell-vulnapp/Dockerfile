FROM eclipse-temurin:8-jdk AS build

WORKDIR /build

# Download dependencies
ADD https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-core/2.14.1/log4j-core-2.14.1.jar .
ADD https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.14.1/log4j-api-2.14.1.jar .

# Copy source
COPY VulnWebApp.java .

# Compile
RUN mkdir -p WEB-INF/classes && \
    javac -cp log4j-core-2.14.1.jar:log4j-api-2.14.1.jar VulnWebApp.java -d WEB-INF/classes

# Add web.xml
COPY web.xml WEB-INF/

# Create WAR
RUN jar -cvf ROOT.war WEB-INF

# ---

FROM jetty:9.4-jdk8

# Copy WAR to Jetty deployment directory
COPY --from=build /build/ROOT.war /var/lib/jetty/webapps/ROOT.war
