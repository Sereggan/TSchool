# TSchool
T-Systems Task  
market-v1 is a first part of task. Based on Spring MVC, Spring Security, Jpa, Mysql.  
You have to clone market-v1 folder, edit tomcat Server 8.X version, Create database market and fill it with default user's profiles and it's authorities(run src/main/resources/script.sql) and run app  
Default credentionals: Kirill : pass - client, Sergey : pass - employee  
market-v2 is the second part of task. Based on Spring boot, Spring Security, Spring Data.  
You have to clone market-v2 folder, Create database market and fill it with default user's profiles and it's authorities(run src/main/resources/script.sql) and run app  
Default credentionals: Kirill : pass - client, Sergey : pass - employee  
market-v2 also has a Unit tests on businness logic with 80% coverage.  
market-v2 has a configured sonarqube plugin. To run it, run sonarqube on your machine,  
run: mvn clean install sonar:sonar  
Go localhost:9000
