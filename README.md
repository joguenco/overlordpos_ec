# Overlord POS Ec
Software de punto de venta basado en uniCenta y OpenBravo para Ecuador.


## Software
* Java 11
* MariaDB 11.4
* Maven

## Install software and configure (Windows)
* Install JDK 11
* Install chocolatey https://community.chocolatey.org/
* Run powershell as Administrator, and then install maven 
```
choco install maven
```

## Important set path of JDK 11 (Windows)
Set JAVA_HOME and set jdk path. Not set jre path. 

## Create database and user
```
CREATE SCHEMA overlord;
CREATE USER 'overlord'@'%' IDENTIFIED BY 'o';
GRANT ALL PRIVILEGES ON overlord.* TO 'overlord'@'%' WITH GRANT OPTION;
```
### Update password user
```
ALTER USER 'overlord'@'%' IDENTIFIED BY 'o';
```

## Compile (Windows)
* Get into overlordpos_ec directory.
* First delete target directory if exist.
* Compile
```
mvn clean package
```
## Compile (GNU/Linux)
```
mvn clean package
```
## Run (Windows)
```
java -jar .\target\overlordpos_ec.jar
```
## Run (GNU/Linux)
```
java -jar target\overlordpos_ec.jar
```
## Run with Netbeans
In menu Tools -> Options

### Windows
![Compile](images/netbeans_windows_maven.png)
### GNU/Linux
![Compile](images/netbeans_linux_maven.png)

## Format date and hour
```
yyyy-MM-dd H:mm:ss
```
## Add in start.bat (Windows)
```
@echo off
echo Setting JAVA_HOME
set JAVA_HOME=C:\Java\openjdk-11.0.4
echo JAVA_HOME: %JAVA_HOME%
echo setting PATH
set PATH=%JAVA_HOME%\bin;%Path%
echo PATH: %PATH%
echo Display java version
java -version
```
## Problems
### GNU/Linux
* Obtain hostname
It seems that InetAddress.getLocalHost() ignores the /etc/resolv.conf, but only looks at the /etc/hosts file. 
Adding the IP and hostname to this file solves the problem and the exception is gone.

* Access from remote host to database
In /etc/mysql/mariadb.conf.d/50-server.cnf, update 127.0.0.1 to 0.0.0.0
bind-address            = 127.0.0.1

## Utils
### Show databases
```
show databases
```
### Show users 
```
SELECT User, Db, Host from mysql.db;
```
### Show users grants 
```
select distinct concat('SHOW GRANTS FOR ', QUOTE(user), '@', QUOTE(host), ';') as query from mysql.user;
```
### Show database version 
```
select version()
```
### Backup database 
```
mysqldump --no-tablespaces --routines --password=yourpass --user=root overlord > overlord.sql
```
### Restore database
```
mariadb -u root -p overlord < overlord.sql
```
