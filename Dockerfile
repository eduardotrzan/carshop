FROM openjdk:13-jdk-alpine3.10

RUN apk add --no-cache bash

COPY server/target/server-1.0-SNAPSHOT.jar /opt/carshop/server/server.jar
COPY start.sh /opt/carshop/server/start.sh

RUN chmod -R o+x /opt/carshop/server/ && chmod 777 /opt/carshop/server/

ENV JAVA_OPTS=-javaagent:/opt/carshop/server/server.jar

WORKDIR /opt/carshop/server/

EXPOSE 8484

CMD /bin/bash /opt/carshop/server/start.sh ${springProfile}
