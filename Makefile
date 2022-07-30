run: compile
	java AirportApp

compile: AirportApp.class AirportBackEnd.class AirportData.class AirportFrontEnd.class AirportLoader.class AirportTests.class CS400Graph.class FlightData.class GraphADT.class

test: compile
	java -jar junit5.jar --class-path . --scan-classpath

AirportApp.class: AirportApp.java
	javac AirportApp.java

AirportBackEnd.class: AirportBackEnd.java
	javac AirportBackEnd.java

AirportData.class: AirportData.java
	javac AirportData.java

AirportFrontEnd.class: AirportFrontEnd.java
	javac AirportFrontEnd.java

AirportLoader.class: AirportLoader.java
	javac AirportLoader.java

AirportTests.class: AirportTests.java
	javac -cp .:junit5.jar AirportTests.java

CS400Graph.class: CS400Graph.java
	javac CS400Graph.java

FlightData.class: FlightData.java
	javac FlightData.java

GraphADT.class: GraphADT.java
	javac GraphADT.java