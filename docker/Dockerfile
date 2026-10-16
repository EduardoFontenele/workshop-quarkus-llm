FROM mysql:8.4

ENV MYSQL_ROOT_PASSWORD=root_password
ENV MYSQL_DATABASE=rh_workshop
ENV MYSQL_USER=rh_user
ENV MYSQL_PASSWORD=rh_password

COPY init.sql /docker-entrypoint-initdb.d/01-init.sql

EXPOSE 3306
