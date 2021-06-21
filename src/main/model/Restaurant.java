package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

// Represents a restaurant with a name, type of cuisine, rating and price.
public class Restaurant implements Saveable {

    private String name;
    private String cuisine;
    private double rating;
    private int price;

    //REQUIRES: - restaurantName and cuisineType are non-empty strings
    //          - rating is a double between 1 and 5
    //          - price is an integer between 1 and 3
    //EFFECTS: constructs a restaurant with given name, type of cuisine, rating and price
    public Restaurant(String restaurantName, String cuisineType, double rating, int price) {
        this.name = restaurantName;
        this.cuisine = cuisineType;
        this.rating = rating;
        this.price = price;

    }

    // EFFECTS: returns the name of a restaurant
    public String getName() {
        return name;
    }

    // EFFECTS: returns the cuisine of a restaurant
    public String getCuisine() {
        return cuisine;
    }

    // EFFECTS: returns the price of a restaurant
    public int getPrice() {
        return price;
    }

    // EFFECTS: returns the rating of a restaurant
    public double getRating() {
        return rating;
    }

    // EFFECTS: prints the name, cuisine, rating, price of a given restaurant onto recommendations.txt
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(name);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(cuisine);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(rating);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(price);
        printWriter.print("\n");
    }

}