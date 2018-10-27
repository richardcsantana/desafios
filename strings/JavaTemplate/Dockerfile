FROM openjdk:11
MAINTAINER Richard Santana
ADD target/*.jar /app.jar
RUN sh -c 'touch /app.jar'
CMD sh -c [java,jar,app.jar]
EXPOSE 8080