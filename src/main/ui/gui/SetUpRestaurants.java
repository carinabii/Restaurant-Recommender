package ui.gui;

import model.Restaurant;
import model.RestaurantList;

import javax.swing.*;


// Instantiates Restaurants used in Application
public class SetUpRestaurants {

    public static ImageIcon chineseFoodImage;
    public static ImageIcon koreanFoodImage;
    public static ImageIcon japaneseFoodImage;
    public static ImageIcon thaiFoodImage;
    public static ImageIcon cozyImage;
    public static ImageIcon modImage;
    public static ImageIcon priceyImage;

    public static Restaurant comeAlong;
    public static Restaurant nori;
    public static Restaurant greenBasil;
    public static Restaurant myst;
    public static Restaurant kokoro;
    public static Restaurant gyuKaku;
    public static Restaurant sura;
    public static Restaurant deerGarden;

//    public RecommendationList recommendations;
    public static RestaurantList resList;

//    public static final String RECOMMENDATIONS_FILE = "./data/recommendations.txt";

    // MODIFIES: this
    // EFFECTS: loads all restaurants and compiles into a list of restaurants
    public static void loadRestaurants() {
        comeAlong = new Restaurant("Come Along", "Chinese", 3.8, 2);
        nori = new Restaurant("Nori Bento & Udon", "Japanese", 3.5, 1);
        greenBasil = new Restaurant("Green Basil", "Thai", 3, 3);
        myst = new Restaurant("Myst", "Chinese", 4, 1);
        kokoro = new Restaurant("Kokoro Tokyo Mazesoba", "Japanese", 4.2, 2);
        gyuKaku = new Restaurant("Gyu-Kaku Japanese BBQ", "Japanese", 4.1, 2);
        sura = new Restaurant("Sura Korean Cuisine", "Korean", 3.9, 3);
        deerGarden = new Restaurant("Deer Garden Signatures", "Chinese", 3.6, 1);
    }

    // MODIFIES: resList
    // EFFECTS: adds loaded restaurants to resList
    public static void compileRestaurants() {
        resList = new RestaurantList();

        resList.addRestaurant(comeAlong);
        resList.addRestaurant(greenBasil);
        resList.addRestaurant(myst);
        resList.addRestaurant(nori);
        resList.addRestaurant(kokoro);
        resList.addRestaurant(gyuKaku);
        resList.addRestaurant(sura);
        resList.addRestaurant(deerGarden);
    }


    // EFFECTS: calls and loads images present with the dropdown menu
    public static void loadImages() {
        String sep = System.getProperty("file.separator");
        chineseFoodImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "dimsumicon.jpg");
        koreanFoodImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "bibimbapicon.jpg");
        japaneseFoodImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "sushiicon.jpg");
        thaiFoodImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "thaifood.jpg");

        cozyImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "cozyicon.jpg");
        modImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "affordableicon.jpg");
        priceyImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "expensiveicon.jpg");

    }

}
