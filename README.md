TODO fill this out a bit

This was setup on a Windows OS, TODO add more notes about assumptions
- download jdk 12.0.1 
  - donwload can be found here: https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html
- download/install maven 3.6.1
  - download can be found here: https://maven.apache.org/download.cgi?Preferred=http%3A%2F%2Fapache.claz.org%2F
  - installation instructions here: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

setup using drop wizard, TODO motivations

```
mvn package
java -jar target/dogapi-1.0-SNAPSHOT.jar config.yml
```

```
Your assignment is to create a RESTful API to track important information about our office dogs. A dog’s schema should contain a unique ID, the dog’s name, the dog’s owner’s name, and notes about the dog. The methods and endpoints should be:

GET /dogs - List all dogs
POST /dogs - Add a new dog 😄
GET /dogs/:id - Get details for one dog
PUT /dogs/:id - Update details for one dog
DELETE /dogs/:id - Remove a dog ☹️
In lieu of a database, details about the dogs should be written to and retrieved from a JSON file titled “dogs.json” in your project’s directory.

Please use any language you’re comfortable with. This job will specifically require Go and Java work, but any server side language is acceptable for this assignment. Publish the source code and a README.md that will tell us how to run the assignment. Please be sure to capture any assumptions you’ve made that we should be aware of.

Lastly, we really don't want you to spend more than 2 hours on this assignment. To that end, please don’t write any tests, unit or otherwise, but do think about how you would test so we can discuss it during your interview.
```