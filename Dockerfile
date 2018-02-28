FROM maven:3.5-alpine

WORKDIR /code
ADD pom.xml /code/pom.xml

RUN mvn dependency:resolve
ADD src /code/src
RUN mvn verify clean compile

CMD mvn exec:java
