FROM openjdk:8u181-jdk

WORKDIR /app

# Install Jetty 9
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-distribution/9.4.43.v20210629/jetty-distribution-9.4.43.v20210629.zip && \
    unzip jetty-distribution-9.4.43.v20210629.zip && \
    mv jetty-distribution-9.4.43.v20210629 jetty

# Add Log4j 2.14.1 jars (expect these to be added externally or mounted)
COPY log4j-core-2.14.1.jar log4j-api-2.14.1.jar ./
COPY log4j2.xml ./log4j2.xml
COPY VulnWebApp.java .

# Compile
RUN javac -cp log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:jetty/lib/servlet-api-3.1.jar VulnWebApp.java

# Package as servlet WAR manually
RUN mkdir -p jetty/webapps/root/WEB-INF/classes && \
    cp VulnWebApp.class jetty/webapps/root/WEB-INF/classes && \
    echo "<web-app><servlet><servlet-name>VulnWebApp</servlet-name><servlet-class>VulnWebApp</servlet-class></servlet><servlet-mapping><servlet-name>VulnWebApp</servlet-name><url-pattern>/</url-pattern></servlet-mapping></web-app>" > jetty/webapps/root/WEB-INF/web.xml

# Expose port
EXPOSE 8080

CMD ["jetty/bin/jetty.sh", "run"]
