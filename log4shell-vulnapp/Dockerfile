FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app
COPY VulnWebApp.java web.xml ./
COPY log4j-core-2.14.1.jar log4j-api-2.14.1.jar javax.servlet-api-3.1.0.jar ./

RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:javax.servlet-api-3.1.0.jar" VulnWebApp.java -d WEB-INF/classes && \
    cp web.xml WEB-INF/web.xml && \
    jar cvf ROOT.war -C WEB-INF .

FROM eclipse/jetty:11-jdk17
COPY --from=builder /app/ROOT.war /var/lib/jetty/webapps/ROOT.war
