
package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendationListTests {

    public Restaurant comeAlong;
    public Restaurant nori;
    public Restaurant greenBasil;
    public Restaurant myst;

    public RestaurantList allRestaurants;
    public RecommendationList emptyRecList;
    public RecommendationList recommendationsList;
    public RecommendationList completed;

    @BeforeEach
    public void setup() {
        comeAlong = new Restaurant("Come Along", "Chinese", 3.8, 2);
        nori = new Restaurant("Nori Bento & Udon", "Japanese", 3.5, 1);
        greenBasil = new Restaurant("Green Basil", "Thai", 3, 2);
        myst = new Restaurant("Myst", "Chinese", 4, 1);

        emptyRecList = new RecommendationList();
        allRestaurants = new RestaurantList();
        recommendationsList = new RecommendationList();
        completed = new RecommendationList();

        allRestaurants.addRestaurant(comeAlong);
        allRestaurants.addRestaurant(greenBasil);
        allRestaurants.addRestaurant(myst);
        allRestaurants.addRestaurant(nori);

        recommendationsList.addRestaurantToRecommendation(comeAlong);
        recommendationsList.addRestaurantToRecommendation(greenBasil);
        recommendationsList.addRestaurantToRecommendation(myst);
    }

    @Test
    public void chooseRestaurantRecommendationTest() {
        assertTrue(recommendationsList.recommendationList.contains(greenBasil));
        recommendationsList.chooseRestaurant("Green Basil");
        assertFalse(recommendationsList.recommendationList.contains(greenBasil));
        assertTrue(recommendationsList.recommendationList.contains(myst));
        assertTrue(recommendationsList.recommendationList.contains(comeAlong));
    }

    @Test
    public void chooseRestaurantNotInRecommendationTest() {
        assertFalse(recommendationsList.recommendationList.contains(nori));
        assertFalse(recommendationsList.chooseRestaurant("Nori Bento & Udon"));
    }

    @Test
    public void displayAllRestaurantsRecommendationTest() {
        recommendationsList.displayAllRestaurantsRecommendation();
    }

    @Test
    public void displayAllRestaurantsRecommendationEmptyTest() {
        emptyRecList.displayAllRestaurantsRecommendation();
    }

    @Test
    public void removeRestaurantTest() {
        assertTrue(recommendationsList.recommendationList.contains(greenBasil));
        recommendationsList.removeRestaurant("Green Basil");
        assertFalse(recommendationsList.recommendationList.contains(greenBasil));
        assertTrue(recommendationsList.recommendationList.contains(myst));
        assertTrue(recommendationsList.recommendationList.contains(comeAlong));

        recommendationsList.removeRestaurant("Myst");
        assertFalse(recommendationsList.recommendationList.contains(myst));

    }

    @Test
    public void removeRestaurantNotInListTest() {
        assertFalse(recommendationsList.recommendationList.contains(nori));
        assertFalse(recommendationsList.removeRestaurant("nori"));
    }

    @Test
    public void lengthTest(){
        assertEquals(emptyRecList.length(), 0);
        assertEquals(recommendationsList.length(), 3);
    }

    @Test
    public void addToCompletedTest(){
        recommendationsList.addToCompleted(2);
        assertEquals(recommendationsList.completed.size(), 1);
        assertTrue(recommendationsList.completed.contains(myst));

        recommendationsList.addToCompleted(0);
        assertEquals(recommendationsList.completed.size(), 2);
        assertTrue(recommendationsList.completed.contains(myst));
        assertTrue(recommendationsList.completed.contains(comeAlong));
    }

    @Test
    public void addToCompletedIntTooBigTest(){
        recommendationsList.addToCompleted(3);
        assertEquals(recommendationsList.completed.size(), 0);
        assertTrue(recommendationsList.completed.isEmpty());
    }

    @Test
    public void getRestaurantTest(){
        assertEquals(recommendationsList.getRestaurant(0), comeAlong);
        assertEquals(recommendationsList.getRestaurant(1), greenBasil);
        assertEquals(recommendationsList.getRestaurant(2), myst);
    }

    @Test
    public void getRestaurantButIntTooBigTest(){
        assertEquals(recommendationsList.getRestaurant(3), null);
    }

    @Test
    public void recContainsTest(){
        assertTrue(recommendationsList.recContains(myst));
        assertFalse(recommendationsList.recContains(nori));

        Restaurant myst2 = new Restaurant("Myst", "Chinese", 4, 1);
        assertTrue(recommendationsList.recContains(myst2));
        Restaurant myst3 = new Restaurant("Myst", "Japanese", 4, 1);
        assertFalse(recommendationsList.recContains(myst3));
        Restaurant myst4 = new Restaurant("Myst", "Chinese", 2, 1);
        assertFalse(recommendationsList.recContains(myst4));
        Restaurant myst5 = new Restaurant("Myst", "Chinese", 4, 2);
        assertFalse(recommendationsList.recContains(myst5));
        Restaurant myst6 = new Restaurant("Nori", "Chinese", 4, 1);
        assertFalse(recommendationsList.recContains(myst6));
    }

}