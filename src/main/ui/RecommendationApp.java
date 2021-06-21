//package ui;
//
//import model.*;
////import persistence.RecommendationReader;
//import persistence.Reader;
//import persistence.Writer;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//import java.util.Scanner;
//
//public class RecommendationApp {
//    public static final String RECOMMENDATIONS_FILE = "./data/recommendations.txt";
//    public Restaurant comeAlong;
//    public Restaurant nori;
//    public Restaurant greenBasil;
//    public Restaurant myst;
//    public Restaurant kokoro;
//    public Restaurant gyuKaku;
//    public Restaurant sura;
//    public Restaurant deerGarden;
//
//    public RecommendationList recommendations;
//    public RestaurantList resList;
//
//    private Scanner input;
//
////    public static final int WIDTH = 1000;
////    public static final int HEIGHT = 600;
////
////    public static JPanel panel;
////    public static JPanel cuisinePanel;
////    public static JFrame frame;
////
////    public static JButton cuisineButton;
////    public static JButton priceButton;
////    public static JButton viewButton;
////    public static JButton resButton;
////
////    public JButton chinese;
////    public JButton japanese;
////    public JButton korean;
////    public JButton thai;
//
//    // EFFECTS: run the recommendation application
//    public RecommendationApp() {
//        runRecommendation();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads restaurants
//
//    // MODIFIES: this
//    // EFFECTS: process user input
//    private void runRecommendation() {
//        boolean keepRunning = true;
//        String action = null;
//        input = new Scanner(System.in);
//
//
//        loadRestaurants();
//        compileRestaurants();
//        loadRecommendations();
//
//        while (keepRunning) {
//            displayChoices();
//            action = input.next();
//            action = action.toLowerCase();
//
//            if (action.equals("exit")) {
//                keepRunning = false;
//            } else {
//                processAction(action);
//            }
//
//        }
//        System.out.println("\nSee you next time!");
//    }
//
//
//
//    // MODIFIES: this
//    // EFFECTS: loads all restaurants and compiles into a list of restaurants
//    private void loadRestaurants() {
//        comeAlong = new Restaurant("Come Along", "Chinese", 3.8, 2);
//        nori = new Restaurant("Nori Bento & Udon", "Japanese", 3.5, 1);
//        greenBasil = new Restaurant("Green Basil", "Thai", 3, 3);
//        myst = new Restaurant("Myst", "Chinese", 4, 1);
//        kokoro = new Restaurant("Kokoro Tokyo Mazesoba", "Japanese", 4.2, 2);
//        gyuKaku = new Restaurant("Gyu-Kaku Japanese BBQ", "Japanese", 4.1, 2);
//        sura = new Restaurant("Sura Korean Cuisine", "Korean", 3.9, 3);
//        deerGarden = new Restaurant("Deer Garden Signatures", "Chinese", 3.6, 1);
//    }
//
//    // MODIFIES: resList
//    // EFFECTS: adds loaded restaurants to resList
//    private void compileRestaurants() {
//        resList = new RestaurantList();
//
//        resList.addRestaurant(comeAlong);
//        resList.addRestaurant(greenBasil);
//        resList.addRestaurant(myst);
//        resList.addRestaurant(nori);
//        resList.addRestaurant(kokoro);
//        resList.addRestaurant(gyuKaku);
//        resList.addRestaurant(sura);
//        resList.addRestaurant(deerGarden);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads recommendations from recommendations.txt, if file exists
//    // otherwise initializes empty recommendationslist
//    private void loadRecommendations() {
//        try {
//            initializeRecommendationList();
//            List<Restaurant> recommended =
//                    Reader.readRestaurants(new File(RECOMMENDATIONS_FILE));
//            for (Restaurant res : recommended) {
//                recommendations.addRestaurantToRecommendation(res);
//            }
//        } catch (IndexOutOfBoundsException e) {
//            initializeRecommendationList();
//        } catch (IOException e) {
//            initializeRecommendationList();
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: creates new recommendation list
//    private void initializeRecommendationList() {
//        recommendations = new RecommendationList();
//    }
//
//    //EFFECTS: saves state of recommendationsList with restaurant input to recommendations.txt
//    private void saveRestaurants() {
//        try {
//            Writer writer = new Writer(new File(RECOMMENDATIONS_FILE));
//            int i = 0;
//            while (recommendations.completed.size() < recommendations.length()) {
//                writer.write(recommendations.getRestaurant(i));
//                recommendations.addToCompleted(i);
//                i++;
//            }
//            writer.close();
//
//            System.out.println("Recommendations saved to file " + RECOMMENDATIONS_FILE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save recommendations to " + RECOMMENDATIONS_FILE);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    // EFFECTS: displays choices of valid command inputs to the user
//    private void displayChoices() {
//        System.out.println("\nSelect an action from the following:");
//        System.out.println("\ncuisine -> organize recommendations by type of cuisine");
//        System.out.println("\nprice -> organize recommendations by price");
//        System.out.println("\nview -> view saved restaurant recommendations list");
//        System.out.println("\nrestaurants -> view all restaurants available in the system");
//        System.out.println("\nsave -> save recommendation data to file");
//        System.out.println("\nexit -> exit the application");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes action inputted by the user
//    private void processAction(String action) {
//        if (action.equals("cuisine")) {
//            organizeByCuisine();
//        } else if (action.equals("price")) {
//            organizeByPrice();
//        } else if (action.equals("view")) {
//            viewStoredRestaurants();
//        } else if (action.equals("restaurants")) {
//            viewAll();
//        } else if (action.equals("save")) {
//            saveRestaurants();
//        } else {
//            System.out.println("Invalid input...");
//        }
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: organizes the list of restaurants by inputted cuisine
//    private void organizeByCuisine() {
//        System.out.println("\n What type of cuisine would you like to enjoy? \nSelect:");
//        System.out.println("\nChinese");
//        System.out.println("\nJapanese");
//        System.out.println("\nThai");
//        System.out.println("\nKorean");
//        String cuisine = input.next();
//
//        resList.specPrice.clear();
//        resList.specCuisine.clear();
//        if (cuisine.equals("Chinese") || cuisine.equals("Japanese")
//                || cuisine.equals("Thai") || cuisine.equals("Korean")) {
//            resList.sortByCuisine(cuisine);
//            System.out.println("To view details of a restaurant, please select:");
//            for (int i = 0; i < resList.specCuisine.size(); i++) {
//                System.out.println(i + " -> " + resList.specCuisine.get(i).getName());
//            }
//            selectRestaurantByCuisine();
//
//        } else {
//            System.out.println("\nInvalid selection...");
//        }
//
//
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: organizes the list of restaurants by inputted price
//    private void organizeByPrice() {
//        System.out.println("\nWhat price point would you like? \nSelect:");
//        System.out.println("\n1 -> $");
//        System.out.println("\n2 -> $$");
//        System.out.println("\n3 -> $$$");
//        int price = input.nextInt();
//
//        resList.specPrice.clear();
//        resList.specCuisine.clear();
//        if (price == 1 || price == 2 || price == 3) {
//            resList.sortByPrice(price);
//            System.out.println("To view details of a restaurant, please select:");
//            for (int i = 0; i < resList.specPrice.size(); i++) {
//                System.out.println(i + " -> " + resList.specPrice.get(i).getName());
//            }
//            selectRestaurantByPrice();
//        } else {
//            System.out.println("\nInvalid selection...");
//        }
//
//    }
//
//    // EFFECTS: prints out details of the selected restaurant
//    private void selectRestaurantByCuisine() {
//        String choice = input.next();
//
//        try {
//            if (choice.equals("back")) {
//                organizeByCuisine();
//            } else {
//                getRestaurantByCuisine(Integer.parseInt(choice));
//            }
//
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid Input...");
//        }
//    }
//
//    // EFFECTS: pushes to printDetail and then addtoRecommendations
//    private void getRestaurantByCuisine(int choice) {
//        printDetails(resList.specCuisine.get(choice));
//        addToRecommendations(resList.specCuisine.get(choice));
//    }
//
//    // EFFECTS: prints out details of the selected restaurant
//    private void selectRestaurantByPrice() {
//        String choice = input.next();
//
//        try {
//            if (choice.equals("back")) {
//                organizeByCuisine();
//            } else {
//                getRestaurantByPrice(Integer.parseInt(choice));
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid Input...");
//        }
//
//    }
//
//    // EFFECTS: pushes to printDetail and then addtoRecommendations
//    private void getRestaurantByPrice(int choice) {
//        printDetails(resList.specPrice.get(choice));
//        addToRecommendations(resList.specPrice.get(choice));
//
//    }
//
//    // EFFECTS: prints details of restaurant
//    public void printDetails(Restaurant res) {
//        System.out.println(res.getName() + " has been selected!");
//        System.out.println("cuisine = " + res.getCuisine() + ", rating = "
//                + res.getRating() + ", price = " + res.getPrice());
//
//    }
//
//    // EFFECTS: adds the inputted restaurant into a list of recommendations
//    private void addToRecommendations(Restaurant res) {
//        System.out.println("Add to recommendations? Type: ");
//        System.out.println("\ny -> yes");
//        System.out.println("\nn -> no");
//        String choice = input.next();
//
//        if (choice.equals("y") && !recommendations.recContains(res)) {
//            recommendations.addRestaurantToRecommendation(res);
//            System.out.println(res.getName() + " has been added!");
//        } else if (choice.equals("y") && recommendations.recContains(res)) {
//            System.out.println("Restaurant already in recommendations!");
//        } else if (choice.equals("n")) {
//            System.out.println("No restaurant added!");
//        } else {
//            System.out.println("Invalid input...");
//        }
//    }
//
//    // EFFECTS: prints out list of names of all recommended restaurants with stored information
//    private void viewStoredRestaurants() {
//        recommendations.displayAllRestaurantsRecommendation();
//    }
//
//    //EFFECTS: prints out list of all stored restaurants in system
//    private void viewAll() {
//        resList.displayAllRestaurants();
//    }
//}