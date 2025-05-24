FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app
COPY VulnWebApp.java .
COPY log4j-api-2.14.1.jar .
COPY log4j-core-2.14.1.jar .
COPY javax.servlet-api-3.1.0.jar .
COPY web.xml .

RUN mkdir -p WEB-INF/classes \
 && javac -cp "log4j-api-2.14.1.jar:log4j-core-2.14.1.jar:javax.servlet-api-3.1.0.jar" VulnWebApp.java -d WEB-INF/classes \
 && mkdir -p WEB-INF/lib \
 && cp log4j-api-2.14.1.jar WEB-INF/lib/ \
 && cp log4j-core-2.14.1.jar WEB-INF/lib/ \
 && cp web.xml WEB-INF/web.xml \
 && jar cvf ROOT.war -C WEB-INF .

FROM tomcat:9.0-jdk17-openjdk
COPY --from=builder /app/ROOT.war /usr/local/tomcat/webapps/ROOT.war