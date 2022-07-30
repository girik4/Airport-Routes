import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.platform.console.ConsoleLauncher;

public class AirportTests {
    public static void main(String[] args) throws Exception {
        String className = MethodHandles.lookup().lookupClass().getName();
        String classPath = System.getProperty("java.class.path").replace(" ", "\\ ");
        String[] arguments = new String[] {
                "-cp",
                classPath,
                "--include-classname=.*",
                "--select-class=" + className };
        ConsoleLauncher.main(arguments);
        // Run All Tests
    }

    // Data Wrangler Code Tests

    // Back End Developer Tests

    @Test
    public void backEndTest1() {
        //tests the shortestPathDistance() method

        AirportBackEndInterface engine = new AirportBackEnd();

        AirportData a1 = new AirportData("Airport1", "1", "USA", "MN");
        AirportData a2 = new AirportData("Airport2", "2", "USA", "WI");
        AirportData a3 = new AirportData("Airport3", "3", "USA", "CO");
        AirportData a4 = new AirportData("Airport4", "4", "USA", "MN");
        AirportData a5 = new AirportData("Airport5", "5", "USA", "HI");

        engine.addAirport(a1);
        engine.addAirport(a2);
        engine.addAirport(a3);
        engine.addAirport(a4);
        engine.addAirport(a5);

        engine.addFlightPath(new FlightData(a1, a2, "10", 3));
        engine.addFlightPath(new FlightData(a2, a3, "11", 2));
        engine.addFlightPath(new FlightData(a3, a4, "12", 5));
        engine.addFlightPath(new FlightData(a4, a5, "13", 7));
        engine.addFlightPath(new FlightData(a5, a1, "14", 4));
        engine.addFlightPath(new FlightData(a2, a5, "15", 6));
        engine.addFlightPath(new FlightData(a4, a2,"16", 3));

        assertEquals(9, engine.shortestPathDistance(a1, a5));
        assertEquals(8, engine.shortestPathDistance(a3, a2));
        assertEquals(9, engine.shortestPathDistance(a5, a3));
        assertEquals(3, engine.shortestPathDistance(a4, a2));

    }

    @Test
    public void backEndTest2() {
        //tests the shortestPathOfCities method

        AirportBackEndInterface engine = new AirportBackEnd();

        AirportData a1 = new AirportData("Airport1", "1", "USA", "MN");
        AirportData a2 = new AirportData("Airport2", "2", "USA", "WI");
        AirportData a3 = new AirportData("Airport3", "3", "USA", "CO");
        AirportData a4 = new AirportData("Airport4", "4", "USA", "MN");
        AirportData a5 = new AirportData("Airport5", "5", "USA", "HI");

        engine.addAirport(a1);
        engine.addAirport(a2);
        engine.addAirport(a3);
        engine.addAirport(a4);
        engine.addAirport(a5);

        engine.addFlightPath(new FlightData(a1, a2, "10", 3));
        engine.addFlightPath(new FlightData(a2, a3, "11", 2));
        engine.addFlightPath(new FlightData(a3, a4, "12", 5));
        engine.addFlightPath(new FlightData(a4, a5, "13", 7));
        engine.addFlightPath(new FlightData(a5, a1, "14", 4));
        engine.addFlightPath(new FlightData(a2, a5, "15", 6));
        engine.addFlightPath(new FlightData(a4, a2,"16", 3));


        assertEquals(engine.shortestPathOfCities(a1, a5).toString(), "[Airport1, Airport2, Airport5]");
        assertEquals(engine.shortestPathOfCities(a3, a2).toString(), "[Airport3, Airport4, Airport2]");
        assertEquals(engine.shortestPathOfCities(a5, a3).toString(), "[Airport5, Airport1, Airport2, Airport3]");
        assertEquals(engine.shortestPathOfCities(a4, a2).toString(), "[Airport4, Airport2]");


    }

    @Test
    public void backEndTest3() {
        //tests the airportsInState() method

        AirportBackEndInterface engine = new AirportBackEnd();

        AirportData a1 = new AirportData("Airport1", "1", "USA", "MN");
        AirportData a2 = new AirportData("Airport2", "2", "USA", "WI");
        AirportData a3 = new AirportData("Airport3", "3", "USA", "CO");
        AirportData a4 = new AirportData("Airport4", "4", "USA", "MN");
        AirportData a5 = new AirportData("Airport5", "5", "USA", "HI");

        engine.addAirport(a1);
        engine.addAirport(a2);
        engine.addAirport(a3);
        engine.addAirport(a4);
        engine.addAirport(a5);

        engine.addFlightPath(new FlightData(a1, a2, "10", 3));
        engine.addFlightPath(new FlightData(a2, a3, "11", 2));
        engine.addFlightPath(new FlightData(a3, a4, "12", 5));
        engine.addFlightPath(new FlightData(a4, a5, "13", 7));
        engine.addFlightPath(new FlightData(a5, a1, "14", 4));
        engine.addFlightPath(new FlightData(a2, a5, "15", 6));
        engine.addFlightPath(new FlightData(a4, a2,"16", 3));

        assertEquals(engine.airportsInState("WI").toString(), "[Airport2]");
        assertEquals(engine.airportsInState("MN").contains("Airport1"), true);
        assertEquals(engine.airportsInState("MN").contains("Airport4"), true);
        assertEquals(engine.airportsInState("CO").toString(), "[Airport3]");
        assertEquals(engine.airportsInState("HI").toString(), "[Airport5]");

    }
    

    // Front End Developer Tests
        @Test
        public void frontEndTest1(){
                AirportBackEndInterface engine = new AirportBackEnd();
                AirportFrontEndInterface fe = new AirportFrontEndPlaceholder();
                TextUITester test = new TextUITester("2\nTX\n5\n");
                fe.run(engine);
                String checkOutput = test.checkOutput();
                boolean passes = true;
                if( !(checkOutput.contains("airports") && checkOutput.startsWith("These ")) ){
                        passes = false;
                        fail();
                }
                assertEquals(passes, true);
        }
	@Test
        public void frontEndTest2(){
                AirportBackEndInterface engine = new AirportBackEnd();
                AirportFrontEndInterface fe = new AirportFrontEndPlaceholder();
                TextUITester test = new TextUITester("3\nAtlantic City International Airport\nACY\nUSA\nNJ\nChicago O'Hare International Airport\nORD\nUSA\nIL\n5\n");
                fe.run(engine);
                String checkOutput = test.checkOutput();
                boolean passes = true;
                if( !(checkOutput.contains("year") && checkOutput.startsWith("All ")) ){
                        passes = false;
                        fail();
                }
                assertEquals(passes, true);
        }
   	@Test
        public void frontEndTest3(){
                AirportBackEndInterface engine = new AirportBackEnd();
                AirportFrontEndInterface fe = new AirportFrontEndPlaceholder();
                TextUITester test = new TextUITester("4\nAtlantic City International Airport\nACY\nUSA\nNJ\nChicago O'Hare International Airport\nORD\nUSA\nIL\n5\n");
                fe.run(engine);
                String checkOutput = test.checkOutput();
                boolean passes = true;
                if( !(checkOutput.contains("cities") || checkOutput.contains("flight")) ){
                        passes = false;
                        fail();
                }
                assertEquals(passes, true);
        }
    // Integration Manager Tests


}
