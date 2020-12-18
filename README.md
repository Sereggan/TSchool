# TSchool
T-Systems Task    
![Java CI with Maven](https://github.com/Sereggan/TSchool/workflows/Java%20CI%20with%20Maven/badge.svg)
  
To run market in docker:  
docker-compose up  
open http://localhost:8081  
open http://localhost:3000
DB loads too long, you have to wait a bit    
Default credentionals: Kirill : 123456 - client, Sergey : 123456 - employee  
  
market has a Unit tests on businness logic with 80% coverage.  
market has a configured sonarqube plugin. To use it, run sonarqube on your machine,    
run: mvn clean install sonar:sonar  
Go localhost:9000  
