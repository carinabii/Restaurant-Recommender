package ui.gui;

import model.RecommendationList;
import model.Restaurant;
import persistence.Reader;
import persistence.Writer;
import ui.gui.exceptions.AlreadyInRecommendedException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static ui.gui.GenerateButtons.*;
import static ui.gui.GenerateMessages.*;
import static ui.gui.SetUpRestaurants.*;

// Instantiates the Graphical User Interface of my application
public class Gui {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    public JPanel panel;
    public JPanel organizedPanel;
    public JPanel savePanel;

    public static JPanel cuisinePanel;
    public static JPanel pricePanel;
    public static JFrame frame;

    public static JButton back;

    public JLabel imageAsLabel;

    public static final String RECOMMENDATIONS_FILE = "./data/recommendations.txt";
    public Restaurant current;

    public RecommendationList recommendations;
    public JComboBox<String> dropDown;

    public Gui() {
        runGUI();
    }

    // EFFECTS: Creates and runs the GUI
    public void runGUI() {

        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        setUpMenuPanel();

        SetUpRestaurants.loadRestaurants();
        SetUpRestaurants.compileRestaurants();
        loadRecommendations();
        SetUpRestaurants.loadImages();

    }


    // MODIFIES: this
    // EFFECTS: loads recommendations from recommendations.txt, if file exists
    // otherwise initializes empty recommendationslist
    private void loadRecommendations() {
        try {
            initializeRecommendationList();
            List<Restaurant> recommended =
                    Reader.readRestaurants(new File(RECOMMENDATIONS_FILE));
            for (Restaurant res : recommended) {
                recommendations.addRestaurantToRecommendation(res);
            }
        } catch (IndexOutOfBoundsException e) {
            initializeRecommendationList();
        } catch (IOException e) {
            initializeRecommendationList();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new recommendation list
    private void initializeRecommendationList() {
        recommendations = new RecommendationList();
    }

    // MODIFIES: this
    // EFFECTS: sets up the initial menu panel
    private void setUpMenuPanel() {
        frame.add(panel);

        panel.setLayout(null);

        back = new JButton("Back");
        back.addActionListener(new BackButton());

        makeMenuButtons();
    }

    // MODIFIES: this
    // EFFECTS: creates the buttons in the initial menu panel
    private void makeMenuButtons() {
        generateMenuButtons();

        cuisineButton.addActionListener(new CuisineAction());
        panel.add(cuisineButton);

        priceButton.addActionListener(new PriceAction());
        panel.add(priceButton);

        viewButton.addActionListener(new ViewAction());
        panel.add(viewButton);

        resButton.addActionListener(new ResAction());
        panel.add(resButton);

        saveButton.addActionListener(new SaveAction());
        panel.add(saveButton);
    }

    // MODIFIES: frame and panel
    // EFFECTS: removes the current panel from the frame
    public static void clearScreen(JPanel pan) {
        frame.remove(pan);
        frame.validate();
        frame.repaint();
    }


    public class CuisineAction implements ActionListener {

        // EFFECTS: clears current screen and initializes button options for cuisinePanel
        @Override
        public void actionPerformed(ActionEvent e) {
            clearScreen(panel);
            createCuisinePanel();
            frame.add(cuisinePanel);

            frame.validate();
            frame.repaint();
        }

        // EFFECTS: creates buttons for cuisinePanel and puts it on cuisinePanel
        public void createCuisinePanel() {

            cuisinePanel = new JPanel();
            cuisinePanel.setLayout(null);
            generateCuisineButtons();

            chinese.addActionListener(new CuisineSorter());
            cuisinePanel.add(chinese);
            korean.addActionListener(new CuisineSorter());
            cuisinePanel.add(korean);
            thai.addActionListener(new CuisineSorter());
            cuisinePanel.add(thai);
            japanese.addActionListener(new CuisineSorter());
            cuisinePanel.add(japanese);

            cuisineTypeLabel();
            cuisinePanel.add(cuisineType);
        }
    }


    public class PriceAction implements ActionListener {

        // EFFECTS: clears current screen and initializes button options for pricePanel
        @Override
        public void actionPerformed(ActionEvent e) {
            clearScreen(panel);
            createPricePanel();
            frame.add(pricePanel);

            frame.validate();
            frame.repaint();
        }

        // EFFECTS: creates buttons for PricePanel and adds them to pricePanel
        public void createPricePanel() {

            pricePanel = new JPanel();
            pricePanel.setLayout(null);
            generatePriceButtons();

            cheap.addActionListener(new PriceSorter());
            pricePanel.add(cheap);
            affordable.addActionListener(new PriceSorter());
            pricePanel.add(affordable);
            expensive.addActionListener(new PriceSorter());
            pricePanel.add(expensive);

            priceTypeLabel();
            pricePanel.add(priceType);


        }
    }

    public class ViewAction implements ActionListener {

        // EFFECTS: displays details of all recommended restaurants
        @Override
        public void actionPerformed(ActionEvent e) {
            clearScreen(panel);
            recommendations.displayAllRestaurantsRecommendation();

            recommendations.pan.add(back);
            back.setBounds(30, 30, 900, 50);
            frame.add(recommendations.pan);
            frame.revalidate();
            frame.repaint();
        }
    }

    public class ResAction implements ActionListener {

        // EFFECTS: displays all restaurants on the screen
        @Override
        public void actionPerformed(ActionEvent e) {
            clearScreen(panel);
            resList.displayAllRestaurants();

            resList.pan.add(back);
            back.setBounds(30, 30, 900, 50);
            frame.add(resList.pan);
            frame.revalidate();
            frame.repaint();
        }
    }

    public class SaveAction implements ActionListener {

        // EFFECTS: provides message that restaurants are saved to file
        @Override
        public void actionPerformed(ActionEvent e) {
            clearScreen(panel);
            savePanel = new JPanel();
            saveRestaurants();

            savePanel.add(back);
            back.setBounds(30, 30, 900, 50);

            frame.add(savePanel);
            frame.revalidate();
            frame.repaint();
        }

        // EFFECTS: saves restaurants to file
        private void saveRestaurants() {
            try {
                Writer writer = new Writer(new File(RECOMMENDATIONS_FILE));
                int i = 0;
                while (recommendations.completed.size() < recommendations.length()) {
                    writer.write(recommendations.getRestaurant(i));
                    recommendations.addToCompleted(i);
                    i++;
                }
                writer.close();

                JLabel saved = new JLabel("Restaurants saved to ./data/recommendations.txt");
                saved.setBounds(30, 350, 900, 50);
                savePanel.add(saved);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to save recommendations to " + RECOMMENDATIONS_FILE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }


    public class CuisineSorter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clearScreen(cuisinePanel);
            organizedPanel = new JPanel();
            organizedPanel.setLayout(null);
            JLabel details = new JLabel("To view details of a restaurant, please select:");
            details.setBounds(350, 0, 900, 50);
            organizedPanel.add(details);

            if (e.getSource() == japanese) {
                setUpImageAsLabel(japaneseFoodImage);
                organizeByCuisine("Japanese");
            } else if (e.getSource() == chinese) {
                setUpImageAsLabel(chineseFoodImage);
                organizeByCuisine("Chinese");
            } else if (e.getSource() == korean) {
                setUpImageAsLabel(koreanFoodImage);
                organizeByCuisine("Korean");
            } else if (e.getSource() == thai) {
                setUpImageAsLabel(thaiFoodImage);
                organizeByCuisine("Thai");
            }

            frame.add(organizedPanel);
            frame.revalidate();
            frame.repaint();

        }
    }

    // MODIFIES: this
    // EFFECTS: organizes the list of restaurants by inputted cuisine and creates a dropdown menu
    private void organizeByCuisine(String cuisine) {
        resList.specCuisine.clear();
        resList.sortByCuisine(cuisine);
        int a = 30;
        List<String> names = new ArrayList<>();
        dropDown = new JComboBox<>();

        for (int i = 0; i < resList.specCuisine.size(); i++) {
            dropDown.addItem(resList.specCuisine.get(i).getName());
        }
        dropDown.setBounds(30, 50, 900, 50);
        dropDown.addActionListener(new ReadDetails());
        organizedPanel.add(dropDown);

    }

    public class PriceSorter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clearScreen(pricePanel);
            organizedPanel = new JPanel();
            organizedPanel.setLayout(null);
            JLabel details = new JLabel("To view details of a restaurant, please select:");
            details.setBounds(350, 0, 900, 50);
            organizedPanel.add(details);

            if (e.getSource() == expensive) {
                setUpImageAsLabel(priceyImage);
                organizeByPrice(3);
            } else if (e.getSource() == affordable) {
                setUpImageAsLabel(modImage);
                organizeByPrice(2);
            } else if (e.getSource() == cheap) {
                setUpImageAsLabel(cozyImage);
                organizeByPrice(1);
            }

            frame.add(organizedPanel);
            frame.revalidate();
            frame.repaint();
        }

    }

    // MODIFIES: this
    // EFFECTS: organizes the list of restaurants by inputted price and creates a dropdown menu
    public void organizeByPrice(int price) {
        resList.specPrice.clear();
        resList.sortByPrice(price);
        dropDown = new JComboBox<>();

        for (int i = 0; i < resList.specPrice.size(); i++) {
            dropDown.addItem(resList.specPrice.get(i).getName());
        }
        dropDown.setBounds(30, 50, 900, 50);
        dropDown.addActionListener(new ReadDetails());
        organizedPanel.add(dropDown);
    }

    public void setUpImageAsLabel(ImageIcon img) {
        imageAsLabel = new JLabel(img);
        imageAsLabel.setBounds(275, 100, 400, 400);
        imageAsLabel.setVisible(true);
        organizedPanel.add(imageAsLabel);
    }

    public class ReadDetails implements ActionListener {

        // EFFECTS: reads the result of selection from dropdown
        @Override
        public void actionPerformed(ActionEvent e) {
            clearScreen(organizedPanel);
            if (e.getSource() == dropDown) {
                JComboBox<String> readString = (JComboBox) e.getSource();
                String input = (String) readString.getSelectedItem();
                printDetails(resList.sameName(input));
            }
        }
    }

    // EFFECTS: prints details of restaurant
    public void printDetails(Restaurant res) {
        current = res;
        organizedPanel = new JPanel();
        organizedPanel.setLayout(null);
        JLabel selected = new JLabel(res.getName() + " has been selected!");
        selected.setBounds(30, 90, 900, 50);
        JLabel details = new JLabel("cuisine = " + res.getCuisine() + ", rating = "
                + res.getRating() + ", price = " + res.getPrice());
        details.setBounds(30, 120, 900, 50);
        organizedPanel.add(selected);
        organizedPanel.add(details);
        JButton saved = new JButton("Add to Recommended? Yes!");
        saved.setBounds(30, 200, 900, 50);
        saved.addActionListener(new Saver());
        GenerateMessages.isInRecMessage();

        back.setBounds(30, 280, 900, 50);
        organizedPanel.add(back);
        organizedPanel.add(alreadyInRec);
        organizedPanel.add(inRec);
        organizedPanel.add(saved);

        frame.add(organizedPanel);
        frame.revalidate();
        frame.repaint();

    }

    public class Saver implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addToRecommendations(current);
        }
    }

    // EFFECTS: saves current restaurant to recommendations
    public void addToRecommendations(Restaurant res) {
        try {
            if (!recommendations.recContains(res)) {
                recommendations.addRestaurantToRecommendation(res);
                inRec.setVisible(true);
                System.out.println(res.getName() + " has been added!");
            } else {
                throw new AlreadyInRecommendedException();
            }
        } catch (AlreadyInRecommendedException e) {
            alreadyInRec.setVisible(true);
        }
    }


    public class BackButton implements ActionListener {

        // EFFECTS: clears the screen and sets up initial menu panel
        @Override
        public void actionPerformed(ActionEvent e) {
            resList.pan.setVisible(false);
            frame.getContentPane().removeAll();
            frame.repaint();
            setUpMenuPanel();
        }
    }

}
