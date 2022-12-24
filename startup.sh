#!/bin/bash
service mysql start
sleep 10
mysql -e "CREATE DATABASE game_list; USE game_list;"
mysql game_list < /usr/src/task5_create_game_table_and_insert_data.sql
mysqladmin -u root password 'example'
java -jar /usr/src/demo-0.0.1-SNAPSHOT.jar
