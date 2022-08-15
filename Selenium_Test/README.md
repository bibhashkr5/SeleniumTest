##Directory structure

|--src
  |--main
  | |--java
  | |--resources
  |--test
  | |--java
  | |--resources
  |-- extent-config.xml
  |--.gitignore
  |--pom.xml
  |--README.md
  

How to run the script

To run individual test

mvn clean test -Denv=dev -Dheadless=true -DtestFile=Test.xml

Where to look for Reports

cd /Reports/TestReport.html

Design Tools Used
Name	Description
TestNg	Used for Assertion and test run
Maven	Used for build creation:
POM	It is a design patter use for UI test

##TestNg version

6.14.3
##Maven Version

1.7
##JVM Version

1.8.0_201