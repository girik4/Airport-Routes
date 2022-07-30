import java.io.FileNotFoundException;
import java.util.*;
import org.w3c.dom.ls.LSException;

interface AirportFrontEndInterface {
    public void run(AirportBackEndInterface engine) throws FileNotFoundException;
}

public class AirportFrontEnd implements AirportFrontEndInterface {
    @Override
    public void run(AirportBackEndInterface engine) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);

        System.out.println("Welcome! Would you like to:");

        int index = 0;
        while (index != 4) {

            System.out.println("1. Search for airport by state");
            System.out.println("2. Find shortest path between two airports");
            System.out.println("3. List of cities that shortest path contains");
            System.out.println("4. Quit");
            index = s.nextInt();

            if (index ==  1) {
                this.searchAirportByState(engine);
            }

            else if (index == 2) {
                this.searchShortestPathDistance(engine);

            }

            else if (index == 3) {
                this.searchShortestPathOfCities(engine);

            }

            else if (index == 4) {
                System.out.println("Goodbye");
            }
            else {
                System.out.println("Invalid input. Please enter an integer 1-5.");
            }

        }
    }

    public void searchAirportByState (AirportBackEndInterface e) {
        Scanner sc = new Scanner (System.in);
        System.out.println("2. Search for airport by state");
        System.out.println("Enter a state you would like to look up airports in? ");
        String state = sc.nextLine();
        List<String> airportsByState = e.airportsInState(state);
        if( !airportsByState.equals(null) ){
            System.out.println("These are all the airports in " + state + " in our datatebase: /n" + airportsByState);
        }
    }

    public void searchShortestPathDistance (AirportBackEndInterface e) {
        Scanner sc = new Scanner (System.in);
        System.out.println("3. Find shortest path between two airports");
        System.out.println("Enter your starting airport: ");
        String source = sc.nextLine();
	System.out.println("Enter your starting airport code: ");
        String code = sc.nextLine();
	System.out.println("Enter the country of the airport: ");
        String country = sc.nextLine();
        System.out.println("Enter the starting state: ");
        String state = sc.nextLine();

        System.out.println("Enter your destination airport? ");
        String destination = sc.nextLine();
	System.out.println("Enter your starting airport code: ");
        String codeD = sc.nextLine();
        System.out.println("Enter the country of the airport: ");
        String countryD = sc.nextLine();
        System.out.println("Enter the state of the destination: ");
        String stateD = sc.nextLine();
        
        AirportData ad = new AirportData(source, code, country, state);
        AirportData ad1 = new AirportData(destination, codeD, countryD, stateD);
        int shortestPathDis = e.shortestPathDistance(ad, ad1);
        if( shortestPathDis == 0 ){
            System.out.println("The shortest path flight from " + source + " to " + destination + " is " + shortestPathDis + ": ");
        } 
    }


    public void searchShortestPathOfCities (AirportBackEndInterface e) throws FileNotFoundException {
        Scanner sc = new Scanner (System.in);
        System.out.println("4. List of cities that shortest path contains");
        System.out.println("Enter your starting airport? ");
        String source = sc.nextLine();
	System.out.println("Enter your starting airport code: ");
        String code = sc.nextLine();
        System.out.println("Enter the country of the airport: ");
        String country = sc.nextLine();
        System.out.println("Enter the starting state: ");
        String state = sc.nextLine();

        System.out.println("Enter your destination airport? ");
        String destination = sc.nextLine();
	System.out.println("Enter your starting airport code: ");
        String codeD = sc.nextLine();
        System.out.println("Enter the country of the airport: ");
        String countryD = sc.nextLine();
        System.out.println("Enter the state of the destination: ");
        String stateD = sc.nextLine();
        
        AirportData ad = new AirportData(source, code, country, state);
        AirportData ad1 = new AirportData(destination, codeD, countryD, stateD);
        List<String> citiesList = e.shortestPathOfCities(ad, ad1);
        if( citiesList.isEmpty() ){
            throw new FileNotFoundException("No connection between these two airports");
        }
        System.out.println("These are the cities in the flight path:/n" + citiesList.toString());
    }
}

class AirportFrontEndPlaceholder implements AirportFrontEndInterface {
    public void run(AirportBackEndInterface engine) {
        System.out.println("This front end has not been implemented yet.");
    }
}
