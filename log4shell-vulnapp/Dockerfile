# Stage 1: Build
FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /app

COPY VulnWebApp.java .
COPY log4j-api-2.14.1.jar .
COPY log4j-core-2.14.1.jar .
COPY javax.servlet-api-3.1.0.jar .

# Compile servlet
RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:javax.servlet-api-3.1.0.jar" VulnWebApp.java -d WEB-INF/classes && \
    mkdir -p WEB-INF && \
    echo '<web-app><servlet><servlet-name>VulnWebApp</servlet-name><servlet-class>VulnWebApp</servlet-class></servlet><servlet-mapping><servlet-name>VulnWebApp</servlet-name><url-pattern>/</url-pattern></servlet-mapping></web-app>' > WEB-INF/web.xml && \
    jar cvf ROOT.war -C WEB-INF .

# Stage 2: Run
FROM jetty:11-jdk17
COPY --from=builder /app/ROOT.war /var/lib/jetty/webapps/ROOT.war