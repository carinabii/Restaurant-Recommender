package model;

import model.exceptions.InvalidCuisineException;
import model.exceptions.InvalidPriceException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// represents a list of restaurants accessible within the application
public class RestaurantList {

    public ArrayList<Restaurant> restaurantList = new ArrayList();
    public ArrayList<Restaurant> specCuisine = new ArrayList<>();
    public ArrayList<Restaurant> specPrice = new ArrayList<>();
    public JPanel pan = new JPanel();

    // EFFECTS: constructs an empty RestaurantList
    public RestaurantList() {
    }

    // REQUIRES: must be a valid restaurant (ie. non-empty name and cuisine, price between 1-3 and rating 1-5)
    // MODIFIES: this
    // EFFECTS: add a restaurant to a list of restaurants
    public void addRestaurant(Restaurant res) {
        this.restaurantList.add(res);
    }

    // REQUIRES: a non-empty list, and string that is a type of cuisine
    // MODIFIES: this
    // EFFECTS: add restaurants from inputted list to newly created list if inputted cuisine matches restaurant cuisine
    public void sortByCuisine(String typeOfCuisine) {
        try {
            isValidCuisine(typeOfCuisine);
            for (Restaurant res : this.restaurantList) {
                if (typeOfCuisine.equals(res.getCuisine())) {
                    specCuisine.add(res);
                }
            }
        } catch (InvalidCuisineException e) {
            System.out.println("Cuisine is Invalid...");
        }

    }

    // EFFECTS: checks that cuisine is a valid cuisine
    public void isValidCuisine(String typeOfCuisine) throws InvalidCuisineException {
        List<String> allCuisines = new ArrayList<>();
        for (Restaurant res : restaurantList) {
            allCuisines.add(res.getCuisine());
        }

        if (!allCuisines.contains(typeOfCuisine)) {
            throw new InvalidCuisineException();
        }
//        if (!typeOfCuisine.equals("Chinese") && !typeOfCuisine.equals("Japanese")
//                && !typeOfCuisine.equals("Thai") && !typeOfCuisine.equals("Korean")) {
//            throw new InvalidCuisineException();
//        }
    }

    // REQUIRES: a non-empty list, and an inputted integer price bounded between 1 and 3
    // MODIFIES: this
    // EFFECTS: add restaurants from inputted list to newly created list if inputted price equals restaurant price
    public void sortByPrice(int price) {
        try {
            isValidPrice(price);
            for (Restaurant res : this.restaurantList) {
                if (price == res.getPrice()) {
                    specPrice.add(res);
                }
            }
        } catch (InvalidPriceException e) {
            System.out.println("Price is Invalid...");
        }

    }

    // EFFECTS: checks that price is a valid price
    public void isValidPrice(int price) throws InvalidPriceException {
        List<Integer> allPrices = new ArrayList<>();
        for (Restaurant res : restaurantList) {
            allPrices.add(res.getPrice());
        }

        if (!allPrices.contains(price)) {
            throw new InvalidPriceException();
        }

    }

    // EFFECTS: gets names of all restaurants in a restaurantList
    public void displayAllRestaurants() {
        pan.setLayout(null);
        pan.setVisible(true);
        JLabel resLabel = new JLabel("RESTAURANTS:");
        resLabel.setBounds(30, 80, 900, 50);
        pan.add(resLabel);
        int i = 90;
        for (Restaurant res : restaurantList) {
            JLabel label = new JLabel(res.getName());
            i = i + 30;
            label.setBounds(30, i, 900, 50);
            pan.add(label);
            System.out.println(res.getName());
        }
    }

    public Restaurant sameName(String str) {
        for (Restaurant res : restaurantList) {
            if (res.getName().equals(str)) {
                return res;
            }
        }
        return null;

    }



}