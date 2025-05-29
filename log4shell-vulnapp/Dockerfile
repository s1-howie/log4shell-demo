FROM tomcat:9.0-jdk8-openjdk

# Create the webapp directory structure
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

# Copy log4j jars
COPY log4j-core-2.14.1.jar /usr/local/tomcat/lib/
COPY log4j-api-2.14.1.jar /usr/local/tomcat/lib/

# Copy Java source
COPY VulnWebApp.java /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# Copy deployment descriptor
COPY web.xml /usr/local/tomcat/webapps/ROOT/WEB-INF/

# Compile the servlet
RUN javac -cp "/usr/local/tomcat/lib/log4j-core-2.14.1.jar:/usr/local/tomcat/lib/log4j-api-2.14.1.jar:/usr/local/tomcat/lib/servlet-api.jar" \
    /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/VulnWebApp.java