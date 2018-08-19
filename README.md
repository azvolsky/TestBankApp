## How to launch project in docker:

* To run app on Win
   > from CMD open project root folder and execute " gradlew bootRun ".

* To run app on Lnx
   > from CMD open project root folder and execute " ./gradlew bootRun ".

## Tomcat container info:

* Tomcat is listening port 8080 [http://localhost:8080](http://localhost:8080).

## DB

* The project is using H2 in memory DB. Starts with project and available by http://localhost:8080/h2-console, the URL is
"jdbc:h2:mem:testdb", the user is "sa"