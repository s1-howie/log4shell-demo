FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app
COPY VulnWebApp.java .
COPY log4j-api-2.14.1.jar .
COPY log4j-core-2.14.1.jar .

RUN mkdir -p WEB-INF/classes && \
    javac -cp "log4j-core-2.14.1.jar:log4j-api-2.14.1.jar" VulnWebApp.java -d WEB-INF/classes && \
    mkdir -p WEB-INF && \
    echo '<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                             http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
  <servlet>
    <servlet-name>VulnWebApp</servlet-name>
    <servlet-class>VulnWebApp</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>VulnWebApp</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
</web-app>' > WEB-INF/web.xml && \
    mkdir -p build/WEB-INF/classes && \
    cp -r WEB-INF/classes build/WEB-INF/ && \
    cp -r WEB-INF/web.xml build/WEB-INF/ && \
    mkdir -p build/META-INF && \
    echo "" > build/META-INF/MANIFEST.MF && \
    jar -cvf ROOT.war -C build .

FROM tomcat:9.0-jdk17-openjdk
COPY --from=builder /app/ROOT.war /usr/local/tomcat/webapps/ROOT.war