package edai.cachedb;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.io.FileWriter;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CacheTest {
    Cache myCache;

    @BeforeEach
    void setUp() {myCache = new Cache();}

    @Test
    void testGetThrowsIfValueExistsWhenTryToAddNewOne() throws IOException {
        FileWriter fileWriter = new FileWriter("cacheStorage.txt");
        myCache.put("1one", "uno", fileWriter);
        myCache.put("2two", "dos", fileWriter);
        myCache.put("3three", "tres", fileWriter);

        assertThrows(DuplicatedKeyException.class, ()-> myCache.addNew("2two","2"));

        fileWriter.close();
    }

    @Test
    void testLoadThreeMapFromFile() throws IOException {
        myCache.loadCache();
        assertEquals(myCache.size(), 3);
        assertTrue(myCache.exists("1one"));
        assertEquals(myCache.get("2two"),"dos");
    }

    @Test
    void testGetThrowaIfKeyOrValueHadInvalidChar() throws IOException {
        assertThrows(KeyInvalidName.class, ()-> myCache.loadEntry("1-one","num1"));
        assertThrows(KeyInvalidName.class, ()-> myCache.loadEntry("2two","num-2"));
    }

    @Test
    void testRemoveEntryInFile() throws IOException {
        FileWriter fileWriter = new FileWriter("cacheStorage.txt");
        myCache.put("1one", "1", fileWriter);
        myCache.put("2two", "2", fileWriter);
        myCache.put("3three", "3", fileWriter);
        fileWriter.close();

        myCache.remove("1one");

        assertEquals(myCache.size(), 2);
        assertFalse(myCache.exists("1one"));


    }

}
