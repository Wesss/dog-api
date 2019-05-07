# Dog API

## Setup
- download java jdk 12.0.1
  - download can be found [here] (https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html)

- download and install maven 3.6.1
  - download can be found [here](https://maven.apache.org/download.cgi?Preferred=http%3A%2F%2Fapache.claz.org%2F)
  - installation instructions [here](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- clone this repository

  ```
  git clone git@github.com:Wesss/dog-api.git
  ```

- compile the application

  ```
  cd dog-api/
  mvn package
  ```

- run the application

  ```
  java -jar target/dogapi-1.0-SNAPSHOT.jar server config.yml
  ```

Requests can now be made to the dog api via `localhost:8080`

## About
This dog api was built in java 12 using [Dropwizard](https://www.dropwizard.io/1.3.9/docs/) and configured using [Maven](https://maven.apache.org/). There are no tests as per instructions, and there is no validation code past what Jackson provides out of the box (Jackson is the JSON parser that comes out of the box with Dropwizard). This was left this way with the time constraints in mind.

