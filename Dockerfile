FROM openjdk:11-slim AS server-build
WORKDIR /app
COPY . .

RUN ["sh", "./gradlew", "build -x test check spotbugsMain spotbugsTest"]

FROM ubuntu:22.04

ENV PORT=8080

COPY --from=server-build /app/build/libs/task5_crud_read_and_create-0.0.1-SNAPSHOT.jar /usr/src
COPY ./sql/task5_create_game_table_and_insert_data.sql /usr/src

RUN apt-get update
RUN apt-get install -y openjdk-11-jre-headless
RUN apt-get install -y mysql-server

RUN rm -rf /var/lib/mysql/*
COPY ./config/mysql/my.cnf /etc/mysql/my.cnf
RUN mysqld --initialize-insecure --user=mysql
RUN chown -R mysql:mysql /var/lib/mysql

COPY ./startup.sh /startup.sh
RUN chmod 755 /startup.sh
CMD /startup.sh











