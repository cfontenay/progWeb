FROM maven
RUN mkdir /app
WORKDIR /app

#Copying sources
COPY pom.xml /app
COPY src /app/src

#Packaging the app
RUN mvn package

FROM tomcat

#Getting the war from stage 0 and uploading to tomcat
COPY --from=0 /app/target/jersey.war webapps/jersey.war

#Authorizing access to manager app
COPY tomcat-users.xml conf
RUN mkdir -p conf/Catalina/localhost
COPY manager.xml conf/Catalina/localhost

EXPOSE 8080

CMD ["./bin/catalina.sh","run"]

