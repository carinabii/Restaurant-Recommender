package ui.gui;

import javax.swing.*;

// Generates buttons for different panels of the application
public class GenerateButtons {

    public static JButton cuisineButton;
    public static JButton priceButton;
    public static JButton viewButton;
    public static JButton resButton;
    public static JButton saveButton;

    public static JButton chinese;
    public static JButton korean;
    public static JButton thai;
    public static JButton japanese;
    public static JButton cheap;
    public static JButton affordable;
    public static JButton expensive;

    public static void generateMenuButtons() {
        cuisineButton = new JButton("Sort Restaurants by Cuisine");
        cuisineButton.setBounds(30, 30, 900, 50);

        priceButton = new JButton("Sort Restaurants by Price");
        priceButton.setBounds(30, 90, 900, 50);

        viewButton = new JButton("View Restaurants Saved to Recommendations");
        viewButton.setBounds(30, 150, 900, 50);

        resButton = new JButton("View All Restaurants");
        resButton.setBounds(30, 210, 900, 50);

        saveButton = new JButton("Save Restaurants to File");
        saveButton.setBounds(30, 270, 900, 50);
    }

    public static void generateCuisineButtons() {
        chinese = new JButton("Chinese");
        chinese.setBounds(30, 90, 900, 50);

        korean = new JButton("Korean");
        korean.setBounds(30, 150, 900, 50);

        thai = new JButton("Thai");
        thai.setBounds(30, 210, 900, 50);

        japanese = new JButton("Japanese");
        japanese.setBounds(30, 270, 900, 50);
    }

    public static void generatePriceButtons() {
        cheap = new JButton("$");
        cheap.setBounds(30, 90, 900, 50);

        affordable = new JButton("$$");
        affordable.setBounds(30, 150, 900, 50);

        expensive = new JButton("$$$");
        expensive.setBounds(30, 210, 900, 50);
    }
}
