package model;

import model.exceptions.InvalidCuisineException;
import model.exceptions.InvalidPriceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantListTest {

    public Restaurant comeAlong;
    public Restaurant nori;
    public Restaurant greenBasil;
    public Restaurant myst;

    public RestaurantList allRestaurants;
    public RestaurantList resList1;
    public RecommendationList recommendationsList;

    @BeforeEach
    public void setup() {
        comeAlong = new Restaurant("Come Along", "Chinese", 3.8, 2);
        nori = new Restaurant("Nori Bento & Udon", "Japanese", 3.5, 1);
        greenBasil = new Restaurant("Green Basil", "Thai", 3, 2);
        myst = new Restaurant("Myst", "Chinese", 4, 1);

        resList1 = new RestaurantList();
        allRestaurants = new RestaurantList();
        recommendationsList = new RecommendationList();

        allRestaurants.addRestaurant(comeAlong);
        allRestaurants.addRestaurant(greenBasil);
        allRestaurants.addRestaurant(myst);
        allRestaurants.addRestaurant(nori);
    }

    @Test
    public void addRestaurantTest() {
        assertTrue(allRestaurants.restaurantList.contains(comeAlong));
        assertTrue(allRestaurants.restaurantList.contains(nori));
        assertTrue(allRestaurants.restaurantList.contains(myst));
        assertTrue(allRestaurants.restaurantList.contains(greenBasil));

        resList1.addRestaurant(greenBasil);
        assertTrue(resList1.restaurantList.contains(greenBasil));
    }

    @Test
    public void sortListByCuisineTest() {
        allRestaurants.sortByCuisine("Chinese");
        assertTrue(allRestaurants.specCuisine.contains(myst));
        assertTrue(allRestaurants.specCuisine.contains(comeAlong));
        assertFalse(allRestaurants.specCuisine.contains(nori));
        assertFalse(allRestaurants.specCuisine.contains(greenBasil));
    }

    @Test
    public void sortListByCuisineEmptyTest() {
        resList1.sortByCuisine("Chinese");
        assertEquals(resList1.restaurantList.size(), 0);
    }

    @Test
    public void sortListByCuisineInvalidInputTest() {
        allRestaurants.sortByCuisine("Korean");
        assertTrue(allRestaurants.specCuisine.isEmpty());
    }

    @Test
    public void isValidCuisineTest(){
        try {
            allRestaurants.isValidCuisine("Chinese");
        } catch (InvalidCuisineException e) {
            fail();
        }

        try {
            allRestaurants.isValidCuisine("Vietnamese");
        } catch (InvalidCuisineException e) {
            // EXCEPTION SHOULD BE CAUGHT
        }
    }

    @Test
    public void sortListByPrice() {
        allRestaurants.sortByPrice(1);
        assertTrue(allRestaurants.specPrice.contains(myst));
        assertTrue(allRestaurants.specPrice.contains(nori));
        assertFalse(allRestaurants.specPrice.contains(comeAlong));
        assertFalse(allRestaurants.specPrice.contains(greenBasil));
    }

    @Test
    public void sortListByPriceEmptyTest() {
        resList1.sortByPrice(2);
        assertEquals(resList1.restaurantList.size(), 0);
    }

    @Test
    public void sortListByPriceInvalidInputTest() {
        allRestaurants.sortByPrice(5);
        assertTrue(allRestaurants.specCuisine.isEmpty());
    }

    @Test
    public void isValidPriceTest(){
        try {
            allRestaurants.isValidPrice(1);
        } catch (InvalidPriceException e) {
            fail();
        }

        try {
            allRestaurants.isValidPrice(5);
        } catch (InvalidPriceException e) {
            // EXCEPTION SHOULD BE CAUGHT
        }
    }

    @Test
    public void displayAllRestaurantsTest() {
        allRestaurants.displayAllRestaurants();
    }

    @Test
    public void displayAllRestaurantsEmptyTest() {
        resList1.displayAllRestaurants();
    }

    @Test
    public void sameNameTest(){
        assertNull(resList1.sameName("Myst"));
        assertEquals(allRestaurants.sameName("Myst"), myst);
    }
    //    @Test
//    public void sortListByRatingTest() {
//        allRestaurants.sortByRatings();
//        assertEquals(allRestaurants.restaurantList.get(0), myst);
//        assertEquals(allRestaurants.restaurantList.get(1), comeAlong);
//        assertEquals(allRestaurants.restaurantList.get(2), nori);
//        assertEquals(allRestaurants.restaurantList.get(3), greenBasil);
//    }

    // add exception tests

}