Otto Systemtest
====================

The Otto Systemtest project is a standalone application to automate the test of Otto Shop by using
 [Selenium](http://www.seleniumhq.org) for Java.

# Required software

1. Java 8
2. Maven 3
3. IntelliJ
4. Google Chrome

# Installation

Clone the project:

    git clone git@github.com:ewasilewski/otto-systemtest.git otto; cd otto

# Configuration

Make sure you add the following JVM args to Defaults Run/Debug Configuration in IntelliJ. For example:

    -ea -Dwebdriver.chrome.driver="otto/src/test/resources/selenium_standalone_binaries/osx/googlechrome/64bit/chromedriver"

Change the path to the current absolute project path.

You can set properties in the properties file ``src/test/resources/otto-systemtest.properties``.

# Start tests

You can start the tests complete or selectively.

## Start the complete test suite.

    mvn verify

## Perform a build without running selenium tests:

    mvn clean verify -P-selenium-tests

## In IntelliJ

You can start the tests by running the maven verify task or selectively by clicking on the test and run Debug.

## Without IntelliJ

	mvn verify -Dwebdriver.chrome.driver="otto/src/test/resources/selenium_standale_binaries/osx/googlechrome/64bit/chromedriver"

# Troubleshooting

## Compile error: Error:java: javacTask: source release 1.8 requires target release 1.8

http://stackoverflow.com/questions/29888592/errorjava-javactask-source-release-8-requires-target-release-1-8#
