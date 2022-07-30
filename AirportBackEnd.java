import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

//interface
interface AirportBackEndInterface {

    //add a new airport to the graph
    public void addAirport(AirportData airport);

    //add a new flight path between airports
    public void addFlightPath(FlightData flight);

    //returns the shortest path distance between two airports as an integer
    public int shortestPathDistance(AirportData source, AirportData destination);

    //returns a list of cities that the shortest path contains between two airports
    public List<String> shortestPathOfCities(AirportData source, AirportData destination);

    //returns a list of airports in a given state
    public List<String> airportsInState(String state);
}

//class
class AirportBackEnd implements AirportBackEndInterface {

    CS400Graph<AirportData> graph = new CS400Graph<>();

    @Override
    public void addAirport(AirportData airport) {
        graph.insertVertex(airport);
    }

    @Override
    public void addFlightPath(FlightData flight) {
        graph.insertEdge(flight.getSource(), flight.getDestination(), flight.getTime());
    }

    @Override
    public int shortestPathDistance(AirportData source, AirportData destination) {
        return graph.getPathCost(source, destination);
    }

    @Override
    public List<String> shortestPathOfCities(AirportData source, AirportData destination) {
        List<String> cities = new LinkedList<>();
        List<AirportData> airports = graph.shortestPath(source, destination);
        for (int i = 0; i < airports.size(); i++) {
            cities.add(airports.get(i).getAirportName());
        }
        return cities;
    }

    @Override
    public List<String> airportsInState(String state) {
        List<String> airports = new LinkedList<>();

        Enumeration<AirportData> keys = graph.vertices.keys();
        AirportData currentData;
        while (keys.hasMoreElements()) {
            currentData = keys.nextElement();
            if (currentData.getState().equals(state)) {
                airports.add(currentData.getAirportName());
            }
        }
        return airports;
    }
}


/*
//placeholder class
class AirportBackEndPlaceholder implements AirportBackEndInterface {

    private AirportData onlyAirport;

    @Override
    public void addAirport(AirportData airport) {
        onlyAirport = airport;
    }

    @Override
    public void addFlightPath(AirportData source, AirportData destination, int duration) {}

    @Override
    public int shortestPathDistance(AirportData source, AirportData destination) {
        return 2;
    }

    @Override
    public List<String> shortestPathOfCities(AirportData source, AirportData destination) {
        List<String> cities = new LinkedList<>();
        cities.add(onlyAirport.getDestinationAirport());
        return cities;
    }

    @Override
    public List<String> airportsInState(String state) {
        return null;
    }
}

 */

