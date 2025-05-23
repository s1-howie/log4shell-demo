FROM openjdk:8u181-jdk
WORKDIR /app
COPY . .
RUN javac -cp log4j-core-2.14.1.jar:log4j-api-2.14.1.jar VulnWebApp.java
EXPOSE 8080
CMD ["java", "-cp", ".:log4j-core-2.14.1.jar:log4j-api-2.14.1.jar", "-Dlog4j.configurationFile=log4j2.xml", "-Dcom.sun.jndi.ldap.object.trustURLCodebase=true", "VulnWebApp"]
