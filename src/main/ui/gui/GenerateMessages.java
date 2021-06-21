package ui.gui;

import javax.swing.*;

// Generates Messages seen by user in application
public class GenerateMessages {

    public static JLabel inRec;
    public static JLabel alreadyInRec;
    public static JLabel priceType;
    public static JLabel cuisineType;

    // EFFECTS: generates messages in response to if in recommended or not
    public static void isInRecMessage() {
        inRec = new JLabel("SAVED TO RECOMMENDATIONS!");
        inRec.setBounds(30, 240, 900, 50);
        inRec.setVisible(false);

        alreadyInRec = new JLabel("Restaurant already in recommendations!");
        alreadyInRec.setBounds(30, 240, 900, 50);
        alreadyInRec.setVisible(false);
    }

    public static void cuisineTypeLabel() {
        cuisineType = new JLabel("What type of cuisine would you like to enjoy?");
        cuisineType.setBounds(30, 30, 900, 50);
    }

    public static void priceTypeLabel() {
        priceType = new JLabel("What price range would you like?");
        priceType.setBounds(30, 30, 900, 50);
    }
}
