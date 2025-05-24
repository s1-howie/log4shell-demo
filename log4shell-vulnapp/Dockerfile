FROM openjdk:8-jdk-slim

WORKDIR /app

# Install required tools
RUN apt-get update && apt-get install -y wget unzip curl

# Download and extract Jetty
RUN wget https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-distribution/9.4.43.v20210629/jetty-distribution-9.4.43.v20210629.zip && \
    unzip jetty-distribution-9.4.43.v20210629.zip && \
    mv jetty-distribution-9.4.43.v20210629 jetty

# Copy application files
COPY log4j-core-2.14.1.jar log4j-api-2.14.1.jar ./
COPY log4j2.xml ./log4j2.xml
COPY VulnWebApp.java .

# Compile the servlet
RUN javac -cp log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:jetty/lib/servlet-api-3.1.jar VulnWebApp.java

# Build WAR-like directory manually
RUN mkdir -p jetty/webapps/root/WEB-INF/classes && \
    cp VulnWebApp.class jetty/webapps/root/WEB-INF/classes && \
    echo "<web-app><servlet><servlet-name>VulnWebApp</servlet-name><servlet-class>VulnWebApp</servlet-class></servlet><servlet-mapping><servlet-name>VulnWebApp</servlet-name><url-pattern>/</url-pattern></servlet-mapping></web-app>" > jetty/webapps/root/WEB-INF/web.xml

EXPOSE 8080

# Set the JVM option to allow JNDI remote class loading (required for Log4Shell)
ENV JAVA_OPTIONS="-Dcom.sun.jndi.ldap.object.trustURLCodebase=true"

CMD java $JAVA_OPTIONS -cp jetty/lib/servlet-api-3.1.jar:log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:. org.eclipse.jetty.start.Main --module=http jetty/etc/jetty.xml
