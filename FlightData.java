import java.util.LinkedList;
// interface

interface FlightDataInterface {
public String getFlightCode();
public int getTime();
public AirportData getSource();
public AirportData getDestination();
}



public class FlightData implements FlightDataInterface {
public AirportData Source;
public AirportData Destination;
public String FlightCode;
public int time;

public FlightData(AirportData Source, AirportData Destination, String FlightCode, int time) {
        this.Source = Source;
        this.Destination = Destination;
        this.FlightCode = FlightCode;
        this.time = time;
}

@Override
public String getFlightCode() {
        return this.FlightCode;
}

@Override
public int getTime() {
        return this.time;
}

@Override
public AirportData getSource() {
        return this.Source;
}

@Override
public AirportData getDestination() {
        return this.Destination;
}

}
