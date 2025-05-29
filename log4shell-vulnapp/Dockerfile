FROM tomcat:9.0-jdk8-openjdk

# === Step 1: Set up vulnerable servlet structure ===
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

# === Step 2: Add vulnerable Log4j jars ===
COPY log4j-core-2.14.1.jar /usr/local/tomcat/lib/
COPY log4j-api-2.14.1.jar /usr/local/tomcat/lib/

# === Step 3: Add Java servlet source and web.xml ===
COPY VulnWebApp.java /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/
COPY web.xml /usr/local/tomcat/webapps/ROOT/WEB-INF/

# === Step 4: Compile servlet ===
RUN javac -cp "/usr/local/tomcat/lib/log4j-core-2.14.1.jar:/usr/local/tomcat/lib/log4j-api-2.14.1.jar:/usr/local/tomcat/lib/servlet-api.jar" \
    /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/VulnWebApp.java

# === Step 5: Add optional JVM flag test ===
# COPY CheckTrustSetting.java /root/
# RUN javac /root/CheckTrustSetting.java

# === Step 6: Force JVM exploit flag at Tomcat runtime ===
CMD ["sh", "-c", "JAVA_OPTS='-Dcom.sun.jndi.ldap.object.trustURLCodebase=true' catalina.sh run"]

EXPOSE 8080