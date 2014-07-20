NWave
=========



  - Simple NWave REST servie
  - Deployes from a jar using SpringBoot
  - Stores data in /log
  - Runs on Port:80 set in \src\main\resources


URL to add data
-----------

```sh

http://aqxtest.ddns.net/inc?id=66498642&time=14-07-11%2008:46:49&signal=12&station=1096&data=28000000000000000000000

http://aqxtest.ddns.net/?id=66498642&time=14-07-11%2008:46:49&signal=12&station=1096&data=28000000000000000000000
```


URL check status of the service
-----------

```sh
http://aqxtest.ddns.net/status

```

Testing the service using CURL
-----------

```sh

curl --data "id=66498642&time=14-07-11%2008:46:49&signal=12&station=1096&data=28000000000000000000000"  http://aqxtest.ddns.net

curl --data "id=66498642&time=14-07-11%2008:46:49&signal=12&station=1096&data=28000000000000000000000"  http://aqxtest.ddns.net/inc

curl -i http://aqxtest.ddns.net/status

```

Version
----

0.0

Tech
-----------

NWave uses Spring boot to create a simple self contained servlet container

Running the Service
--------------

Build the jar file

```sh
  gradle build

```

run the Jar


```sh
  java -jar build/libs/nwave-rest-service-0.1.0.jar

```

If you are using Gradle, you can run your service at the command line this way:

```sh
gradle clean build && java -jar build/libs/nwave-rest-service-0.1.0.jar

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


