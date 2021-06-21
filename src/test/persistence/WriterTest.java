package persistence;


import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testList.txt";
    private Writer testWriter;
    private RecommendationList recommendationList;
    private Restaurant comeAlong;
    private Restaurant nori;
    private Restaurant greenBasil;
    private Restaurant myst;

    @BeforeEach
    public void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        comeAlong = new Restaurant("Come Along", "Chinese", 3.8, 2);
        nori = new Restaurant("Nori Bento & Udon", "Japanese", 3.5, 1);
        greenBasil = new Restaurant("Green Basil", "Thai", 3, 2);
        myst = new Restaurant("Myst", "Chinese", 4, 1);


        testWriter = new Writer(new File(TEST_FILE));
//        recommendationList = new RecommendationList();
//        recommendationList.addRestaurantToRecommendation(myst);
//        recommendationList.addRestaurantToRecommendation(greenBasil);
    }

    @Test
    public void testWriteRestaurants(){
        try {
            testWriter.write(comeAlong);
        } catch (FileNotFoundException e) {
            fail();
        } catch (UnsupportedEncodingException e) {
            fail();
        }

        try {
            testWriter.write(nori);
        } catch (FileNotFoundException e) {
            fail();
        } catch (UnsupportedEncodingException e) {
            fail();
        }
        testWriter.close();

        try {
            List<Restaurant> recommended = Reader.readRestaurants(new File(TEST_FILE));

            comeAlong = recommended.get(0);
            assertEquals("Come Along", comeAlong.getName());
            assertEquals("Chinese", comeAlong.getCuisine());
            assertEquals(2, comeAlong.getPrice());
            assertEquals(3.8, comeAlong.getRating());

            nori = recommended.get(1);
            assertEquals("Nori Bento & Udon", nori.getName());
            assertEquals("Japanese", nori.getCuisine());
            assertEquals(1, nori.getPrice());
            assertEquals(3.5, nori.getRating());

        } catch (IOException e) {
            fail();
        }
    }



}
