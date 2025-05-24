FROM eclipse-temurin:8-jdk AS builder

WORKDIR /app

# Download necessary jars
ADD https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.14.1/log4j-api-2.14.1.jar .
ADD https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-core/2.14.1/log4j-core-2.14.1.jar .
ADD https://repo1.maven.org/maven2/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar .

# Add source files
COPY VulnWebApp.java .

# Compile servlet
RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:javax.servlet-api-3.1.0.jar" VulnWebApp.java -d WEB-INF/classes && \
    echo '<web-app><servlet><servlet-name>VulnWebApp</servlet-name><servlet-class>VulnWebApp</servlet-class></servlet><servlet-mapping><servlet-name>VulnWebApp</servlet-name><url-pattern>/</url-pattern></servlet-mapping></web-app>' > WEB-INF/web.xml && \
    jar cvf ROOT.war -C WEB-INF .

FROM jetty:9.4-jdk8

COPY --from=builder /app/ROOT.war /var/lib/jetty/webapps/ROOT.war