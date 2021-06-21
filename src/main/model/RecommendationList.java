
package model;

import javax.swing.*;
import java.util.ArrayList;


// represents a list of recommendations saved by the user
public class RecommendationList {

    public ArrayList<Restaurant> recommendationList = new ArrayList<>();
    public ArrayList<Restaurant> completed = new ArrayList<>();
    private ArrayList<Boolean> contained = new ArrayList<>();
    public JPanel pan;

    // EFFECTS: constructs an empty RecommendationList
    public RecommendationList() {
    }

    // REQUIRES: added restaurant must be a restaurant saved within the system
    // MODIFIES: this
    // EFFECTS: add a restaurant to the recommendationList
    public void addRestaurantToRecommendation(Restaurant res) {
        this.recommendationList.add(res);
    }


    // MODIFIES: this
    // EFFECTS: returns details of restaurant with inputted name from recommendationList
    public boolean chooseRestaurant(String name) {
        for (Restaurant res : recommendationList) {
            if (res.getName().equals(name)) {
                recommendationList.remove(res);
            }
        }
        return false;
    }

    // EFFECTS: prints string names of all restaurants in the list
    public void displayAllRestaurantsRecommendation() {
        pan = new JPanel();
        pan.setLayout(null);
        JLabel recommended = new JLabel("RECOMMENDED:");
        recommended.setBounds(30, 80, 900, 50);
        pan.add(recommended);
        System.out.println("RECOMMENDED:\n");
        int a = 90;
        for (Restaurant res : recommendationList) {
            JLabel restaurant = new JLabel(res.getName() + ":" + "   cuisine = " + res.getCuisine() + ", rating = "
                    + res.getRating() + ", price = " + res.getPrice());
            a = a + 30;
            restaurant.setBounds(30, a, 900, 50);
            pan.add(restaurant);
//            System.out.println(res.getName() + ":" + "   cuisine = " + res.getCuisine() + ", rating = "
//                    + res.getRating() + ", price = " + res.getPrice());
        }
        System.out.println("\n");
    }

    // EFFECTS: returns the restaurant with corresponding index in the recommendationList
    public Restaurant getRestaurant(int i) {
        if (recommendationList.size() > i) {
            return recommendationList.get(i);
        }
        return null;
    }

    // EFFECTS: prints out the size of the recommendationList
    public int length() {
        return recommendationList.size();
    }

    // MODIFIES: completed
    // EFFECTS: adds element with corresponding index to completed
    public void addToCompleted(int i) {
        if (recommendationList.size() > i) {
            completed.add(recommendationList.get(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: removes restaurant with given name from the recommendationList
    public boolean removeRestaurant(String name) {
        for (Restaurant res : recommendationList) {
            if (res.getName().equals(name)) {
                return recommendationList.remove(res);
            }
        }
        return false;
    }

    // EFFECTS: checks if list contains restaurant with identical name, cuisine, rating and price
    public boolean recContains(Restaurant restaurant) {
        allTrue(restaurant);
        return contained.contains(Boolean.TRUE);
    }

    // EFFECTS: helper method for recContains; checks if 2 objects are the same
    public void allTrue(Restaurant restaurant) {
        contained.clear();
        for (Restaurant res : recommendationList) {
            if (res.getPrice() == restaurant.getPrice() && res.getRating() == restaurant.getRating()
                    && res.getName().equals(restaurant.getName()) && res.getCuisine().equals(restaurant.getCuisine())) {
                contained.add(Boolean.TRUE);
            } else {
                contained.add(Boolean.FALSE);
            }
        }
    }
}