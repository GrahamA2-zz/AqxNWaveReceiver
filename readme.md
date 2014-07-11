NWave
=========



  - Simple NWave REST servie
  - Deployes from a jar using SpringBoot
  - Stores data in /log
  - Runs on Port:80 set in \src\main\resources


Version
----

0.0

Tech
-----------

NWave uses Spring boot to create a simple self contined servlet container

Running the Service
--------------

Build the jar file

```sh
  gradle build

```

run the Jar


```sh
  java -jar build/libs/gs-rest-service-0.1.0.jar

```

If you are using Gradle, you can run your service at the command line this way:

```sh
gradle clean build && java -jar build/libs/gs-rest-service-0.1.0.jar

```

You can alternatively run the app directly from Gradle like this:

```sh
  gradle bootRun
```



> **NOTE:**
>  The procedure above will create a runnable JAR. You can also opt to build a classic [WAR](http://spring.io/guides/gs/convert-jar-to-war/) file instead.

License
----

Aquamatix Development - Not for use in production environments


