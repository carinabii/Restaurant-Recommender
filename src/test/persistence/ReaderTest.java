package persistence;

import model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    public void testParseRestaurantsFile() {
        try {
            List<Restaurant> recommended = Reader.readRestaurants(new File("./data/testResFile1.txt"));

            Restaurant greenBasil = recommended.get(0);
            assertEquals("Green Basil", greenBasil.getName());
            assertEquals("Thai", greenBasil.getCuisine());
            assertEquals(3, greenBasil.getPrice());
            assertEquals(3.0, greenBasil.getRating());

            Restaurant myst = recommended.get(1);
            assertEquals("Myst", myst.getName());
            assertEquals("Chinese", myst.getCuisine());
            assertEquals(1, myst.getPrice());
            assertEquals(4.0, myst.getRating());

        } catch (IOException e) {
            fail();
        }

    }

    @Test
    public void testIOException() {
        try {
            Reader.readRestaurants(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}