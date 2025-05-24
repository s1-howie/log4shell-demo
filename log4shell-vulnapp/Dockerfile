
FROM eclipse-temurin:8-jdk

# Install Jetty
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-distribution/9.4.44.v20210927/jetty-distribution-9.4.44.v20210927.zip && \
    unzip jetty-distribution-9.4.44.v20210927.zip && \
    mv jetty-distribution-9.4.44.v20210927 /opt/jetty

# Set environment variables
ENV JETTY_HOME /opt/jetty
ENV PATH $JETTY_HOME/bin:$PATH

# Add log4j jars
ADD log4j-api-2.14.1.jar .
ADD log4j-core-2.14.1.jar .

# Add VulnWebApp source and compile
ADD VulnWebApp.java .
RUN javac -cp log4j-core-2.14.1.jar:log4j-api-2.14.1.jar:$JETTY_HOME/lib/servlet-api-3.1.jar VulnWebApp.java

# Configure Jetty webapp
RUN mkdir -p $JETTY_HOME/webapps/root/WEB-INF/classes && \
    cp VulnWebApp.class $JETTY_HOME/webapps/root/WEB-INF/classes && \
    echo '<web-app><servlet><servlet-name>VulnWebApp</servlet-name><servlet-class>VulnWebApp</servlet-class></servlet><servlet-mapping><servlet-name>VulnWebApp</servlet-name><url-pattern>/</url-pattern></servlet-mapping></web-app>' > $JETTY_HOME/webapps/root/WEB-INF/web.xml

EXPOSE 8080
CMD ["java", "-cp", "$JETTY_HOME/lib/*:.", "org.eclipse.jetty.start.Main"]
