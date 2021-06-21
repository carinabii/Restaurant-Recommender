package persistence;

import model.Restaurant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// code referenced from TellerApp
// Reads restaurant data from a given file
public class Reader {
    public static final String DELIMITER = ",";
    public static List<Restaurant> recommendationsList;

    // EFFECTS: returns a recommendationList from file
    // throws IO exception if exception raised when opening / reading from file
    public static List<Restaurant> readRestaurants(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return analyzedContent(fileContent);
    }

    // EFFECTS: returns list of restaurants parsed from list of strings
    // each string contains data for one restaurant
    private static List<Restaurant> analyzedContent(List<String> fileContent) {
        recommendationsList = new ArrayList<>();

        for (String str : fileContent) {
            ArrayList<String> strComponents = split(str);
            recommendationsList.add(analyzedRestaurant(strComponents));
        }
        return recommendationsList;
    }

    // EFFECTS: returns content of file as list of strings
    // each string contains content of one row of file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns list of strings by splitting line on DELIMITER
    private static ArrayList<String> split(String str) {
        String[] splits = str.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: has 4 components
    //          - element 0 is the name
    //          - element 1 is the type of cuisine
    //          - element 2 is the rating
    //          - element 0 is the price
    private static Restaurant analyzedRestaurant(List<String> components) {
        String name = components.get(0);
        String cuisine = components.get(1);
        double rating = Double.parseDouble(components.get(2));
        int price = Integer.parseInt(components.get(3));
        return new Restaurant(name, cuisine, rating, price);
    }
}