[![Build Status](https://travis-ci.com/vroegop/Luminis.svg?branch=master)](https://travis-ci.com/vroegop/Luminis)

# Getting Started

## Module explanation

This project has two modules:

* Backend
* Frontend

Both modules contain the relevant part of this application.

Normally, I would setup a build plugin in Maven to trigger a frontend build
via NPM and copy over the generated files to the right location. This might be
the `resources/public` folder location (where I copied them by hand now)
or an actual frontend hosting server via the build pipeline.

## How does the app build work

1. Trigger a frontend build
    * `cd ./frontend`
    * `npm install`
    * `npm run build`
2. Copy the files to the right location
    * Source files are found in `./frontend/build/*`
    * Copy them to your frontend hosting server
    * In this case for simplicity purposes: `backend/src/main/resources/static`
    * This could be automated by the eirslett mvn plugin
3. Trigger the backend build and start the application
    * `mvn clean install spring-boot:run`

## How does the app itself work

1. Start the app (it comes with frontend precompiled):
    * `mvn install spring-boot:run`h
2. Open the dashboard:
    * `http://localhost:8080/index.html`
3. Upload your file (CSV or XML only)
4. A report will be generated for every file uploaded
5. Click <kbd>Open Details</kbd> to see what's up with your data

If everything is okay, the report card will be green and contain no details.
If a record is incorrect, you get a report and the report card will be red.

## Improvements I would suggest having more time

My first change suggestion would be a message queue. I did not integrate
one to save time and make the local build have minimal requirements for 
this demo, but Kafka or another message queue implementation would be a
real improvement. If file processing would take a long time a message
queue would prevent the browser from killing the backend because a
message queue would be capable of distributing the load gradually.

My second suggestion would be a direct connection between the source of
these files and the backend / message queue. These files are obviously
generated by a computer so instead of a user uploading them manually,
it could be possible to upload them automatically.

The above suggestion would require data storage, so this might not always
be feasible if that is not desired (which I have been told that it is not).

The file seems to be encoded using ISO-8859-2 encoding. This is not supported
by default in JavaScript. One of my next fixes would be to fix this encoding
and preventing characters to be lost in translation.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)
* [Spring Data Reactive MongoDB](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-mongodb)
