FROM openjdk:8u292-slim-buster

VOLUME ["/app"]

WORKDIR /app

COPY ./service1/target/service1-1.0-SNAPSHOT.jar /app

CMD java -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT+08  -server -Xms128m -Xmx128m -XX:MetaspaceSize=32m -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./logs/java_heapdump.hprof -XX:-UseLargePages -Xloggc:./logs/service1_gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -Dspring.profiles.active=testing -jar service1-1.0-SNAPSHOT.jar