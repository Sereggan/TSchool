# TSchool
T-Systems Task    
![Java CI with Maven](https://github.com/Sereggan/TSchool/workflows/Java%20CI%20with%20Maven/badge.svg)
  
market is the main part of the task
Based on Spring boot, Spring Security, Spring Data.  
You have to clone market-v2 folder, Create database marketdb and fill it with default user's profiles and it's authorities(run src/main/resources/script.sql) and run app  
Default credentionals: Kirill : 123456 - client, Sergey : 123456 - employee  
  
market has a Unit tests on businness logic with 80% coverage.  
market has a configured sonarqube plugin. To use it, run sonarqube on your machine,    
run: mvn clean install sonar:sonar  
Go localhost:9000  
  
To run React app in docker:  
cd articles  
docker build -t <username>/articles-app .  
docker run --name articles-app -p 4680:3000 -d <username>/articles-app  
visit http://localhost:4680/
  
To run market in docker:  
docker-compose up  
If spring app cant connect to db, try to reload spring app manually
