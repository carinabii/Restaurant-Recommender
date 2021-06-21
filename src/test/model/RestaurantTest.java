package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant comeAlong;
    Restaurant nori;

    @BeforeEach
    public void constructRestaurantTest() {
        comeAlong = new Restaurant("Come Along", "Chinese", 3.8, 2);
        nori = new Restaurant("Nori Bento & Udon", "Japanese", 3.5, 1);
    }

    @Test
    public void getDetails() {
        assertEquals(comeAlong.getName(), "Come Along");
        assertEquals(comeAlong.getCuisine(), "Chinese");
        assertEquals(comeAlong.getPrice(), 2);
        assertEquals(comeAlong.getRating(), 3.8);

        assertEquals(nori.getName(), "Nori Bento & Udon");
        assertEquals(nori.getCuisine(), "Japanese");
        assertEquals(nori.getRating(), 3.5);
        assertEquals(nori.getPrice(), 1);
    }

    // add tests to handle exceptions eg. invalid name, rating, etc.

}