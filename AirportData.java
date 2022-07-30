import java.util.LinkedList;



// interface

interface AirportDataInterface {
public String getAirportName();
public String getAirportCode();
public String getCountry();
public String getState();
}

// public class (implemented primarilly in final app week)

public class AirportData implements AirportDataInterface {

private String AirportName;
private String AirportCode;
private String Country;
private String State;
private LinkedList<FlightData> planesLeaving;

public AirportData(String AirportName, String AirportCode, String Country, String State) {
        this.AirportName = AirportName;
        this.AirportCode = AirportCode;
        this.Country = Country;
        this.State = State;
        this.planesLeaving = new LinkedList<>();
}

@Override
public String getAirportName() {
        return this.AirportName;
}

@Override
public String getAirportCode() {
        return this.AirportCode;
}

@Override
public String getCountry() {
        return this.Country;
}

@Override
public String getState() {
        return this.State;
}


}
