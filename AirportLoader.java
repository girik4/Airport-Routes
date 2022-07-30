import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;

// interface

interface AirportLoaderInterface {
public List<AirportData> loadFile(String csvFilePath) throws FileNotFoundException;
public List<FlightData> loadFlightFile(String csvFilePath,  List<AirportData> AirportList) throws FileNotFoundException;
}

// public class

public class AirportLoader implements AirportLoaderInterface {

@Override
public List<AirportData> loadFile(String csvFilePath) throws FileNotFoundException {

        List<AirportData> Airportlist = new LinkedList<AirportData>();

        File csv = new File(csvFilePath);
        if (!csv.isFile()) {
                throw new FileNotFoundException("File not found");
        }
        int Name = 0;
        int code = 0;
        int country = 0;
        int state =0;
        int counter = 0;
        boolean even = true;
        Scanner scnr = new Scanner(csv);
        if(!scnr.hasNextLine()) {
                scnr.close();
                throw new FileNotFoundException("Uh oh");
        }
        String Heading[] = scnr.nextLine().split(",");
        for (int i = 0; i < Heading.length; i++) {

                if (Heading[i].equals("IATA_CODE")) {
                        code = i;
                }
                if (Heading[i].equals("AIRPORT"))  {
                        Name = i;
                }
                if (Heading[i].equals("COUNTRY")) {
                        country = i;
                }
                if (Heading[i].equals("STATE")) {
                        state = i;
                }


        }

        while (scnr.hasNextLine()) {
                int start = 0;
                int j = 0;
                String[] words = new String[Heading.length];
                String vals = scnr.nextLine();
                for (int i = 0; i < vals.length(); i++) {
                        String word;

                        if (vals.charAt(i) == ',') {
                                if (even == true) {
                                        word = vals.substring(start, i);
                                        start = i;
                                        words[j] = word;
                                        j++;
                                }
                        }
                }

                String AirportName;
                String Code;
                String Country;
                String State;

                AirportName = words[Name].replace(",", "");
                Code = words[code].replaceFirst(",", "");
                Country = words[country].replaceFirst(",", "");
                State = words[state].replace(",", "");

                AirportData ad = new AirportData(AirportName, Code, Country, State);
                Airportlist.add(ad);
        }
        scnr.close();
        return Airportlist;
}

public List<FlightData> loadFlightFile(String csvFilePath,   List<AirportData> AirportList) throws FileNotFoundException {

        List<FlightData> Flightlist = new LinkedList<FlightData>();


        File csv = new File(csvFilePath);
        if (!csv.isFile()) {
                throw new FileNotFoundException("File not found");
        }
        int source = 0;
        int destination = 0;
        int flightNumber = 0;
        int time =0;
        int counter = 0;
        boolean even = true;
        Scanner scnr = new Scanner(csv);
        if(!scnr.hasNextLine()) {
                scnr.close();
                throw new FileNotFoundException("Uh oh");
        }
        String Heading[] = scnr.nextLine().split(",");
        for (int i = 0; i < Heading.length; i++) {

                if (Heading[i].equals("ORIGIN_AIRPORT")) {
                        source = i;
                }
                if (Heading[i].equals("DESTINATION_AIRPORT"))  {
                        destination = i;
                }
                if (Heading[i].equals("FLIGHT_NUMBER")) {
                        flightNumber = i;
                }
                if (Heading[i].equals("SCHEDULED_TIME")) {
                        time = i;
                }
        }
outer:
        while (scnr.hasNextLine()) {
                int start = 0;
                int j = 0;
                String[] words = new String[Heading.length];
                String vals = scnr.nextLine();
                for (int i = 0; i < vals.length(); i++) {
                        String word;

                        if (vals.charAt(i) == ',') {
                                if (even == true) {
                                        word = vals.substring(start, i);
                                        start = i;
                                        words[j] = word;
                                        j++;
                                }
                        }
                }
                String FlightNumber;
                String Sourcename;
                String Destinationname;
                int Time;

                FlightNumber = words[flightNumber].replace(",", "");
                Sourcename = words[source].replaceFirst(",", "");
                Destinationname = words[destination].replaceFirst(",", "");
                Time = Integer.parseInt(words[time].replace(",", ""));
                AirportData Source;
                AirportData Destination;
                AirportData temp =  new AirportData("a","b","c","d");
                int i=0;
                while(i<322)
                {

                        temp = AirportList.get(i);

                        if(temp.getAirportCode().equals(Sourcename))
                        {

                                i++;
                                break;
                        }
                        i++;
                }
                Source = temp;
                int q=0;
                while(q<322)
                {
                        temp = AirportList.get(q);
                        if(temp.getAirportCode().equals(Destinationname))
                        {

                                q++;
                                break;
                        }
                        q++;
                }
                Destination = temp;

                FlightData ad = new FlightData( Source, Destination,FlightNumber, Time);
                Flightlist.add(ad);
        }
        scnr.close();
        return Flightlist;
}
}
