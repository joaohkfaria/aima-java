package aima.core.environment.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Kersul on 07/11/16.
 */
public class FileMap extends ExtendableMap {

    private String mapName;

    public FileMap(String fileName) {
        initMap(fileName);
    }

    private void initMap(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();

            // Getting Map Name
            this.mapName = br.readLine();

            // Getting the number of cities
            int numCities = Integer.parseInt(br.readLine());

            // Setting map positions
            for (int i = 0; i < numCities; i++) {
                String cityLine = br.readLine();
                String[] cities = cityLine.split("\\s+");
                this.setPosition(cities[0], Double.parseDouble(cities[1]), Double.parseDouble(cities[2]));
            }

            // Getting Num of Routes
            int numRoutes = Integer.parseInt(br.readLine());

            // Getting routes
            for (int i = 0; i < numRoutes; i++) {
                String routeLine = br.readLine();
                String[] routes = routeLine.split("\\s+");
                this.addBidirectionalLink(routes[0], routes[1], Double.parseDouble(routes[2]));
            }

            System.out.println("Map Name: " + mapName);
            System.out.println("Locations: " + this.getLocations().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMapName() {
        return mapName;
    }

}
