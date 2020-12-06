FROM openjdk:8
ADD target/market-mysql.jar market-mysql.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "market-mysql.jar"]