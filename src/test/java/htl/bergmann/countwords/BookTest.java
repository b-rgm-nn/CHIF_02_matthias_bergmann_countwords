package htl.bergmann.countwords;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias
 */
public class BookTest {
    
    public BookTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCountWords() {
        Book instance = new Book("test", "the quick brown fox jumped over the lazy dog, the fox jumped. over the dog");
        HashMap<String, Integer> result = instance.countWords();
        assertEquals((int) result.get("the"), 4);
        assertEquals((int) result.get("quick"), 1);
        assertEquals((int) result.get("brown"), 1);
        assertEquals((int) result.get("fox"), 2);
        assertEquals((int) result.get("jumped"), 2);
        assertEquals((int) result.get("over"), 2);
        assertEquals((int) result.get("lazy"), 1);
        assertEquals((int) result.get("dog"), 2);
    }
}
