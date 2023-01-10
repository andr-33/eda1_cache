package edai.cachedb;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TreeMapTest {

    MyTreeMap<String, Integer> map;
    @BeforeEach
    void setup(){
        map = new MyTreeMap<>();
    }

    @Test
    void testNewDictionary(){
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertArrayEquals(new Integer[]{}, map.listValues());
        assertArrayEquals(new String[]{}, map.listKeys());
    }


    @Test
    void testPutOneValue() {
        map.put("one", 1);

        assertFalse(map.isEmpty());
        assertEquals(1, map.size());
        assertArrayEquals(new Integer[]{1}, map.listValues());
        assertArrayEquals(new String[]{"one"}, map.listKeys());
    }


    @Test
    void testPutThreeValue(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertFalse(map.isEmpty());
        assertEquals(3, map.size());
        assertArrayEquals(new Integer[]{1, 2, 3}, map.listValues());
        assertArrayEquals(new String[]{"1one", "2two", "3three"}, map.listKeys());
    }

    @Test
    void testReplaceOneValue(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 100);

        map.put("1one", 1);

        assertFalse(map.isEmpty());
        assertEquals(3, map.size());
        assertArrayEquals(new Integer[]{1, 2, 3}, map.listValues());
        assertArrayEquals(new String[]{"1one", "2two", "3three"}, map.listKeys());
    }

    @Test
    void testContainKey(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertTrue(map.exist("1one"));
    }

    @Test
    void testNotContainKey(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertFalse(map.exist("4four"));
    }

    @Test
    void testGetValue() {
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertEquals(1, map.getValueByKey("1one"));

         System.out.println(Arrays.toString(map.listData()));
    }

    @Test
    void testGetThrowsIfValueDoesNotExists(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertThrows(KeyNotFoundException.class, () -> map.getValueByKey("4four"));
    }

    @Test
    void removeNoKeyReturnsFalse(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertFalse(map.removeOneByKey("4four"));

        assertFalse(map.isEmpty());
        assertEquals(3, map.size());
        assertArrayEquals(new Integer[]{1, 2, 3}, map.listValues());
        assertArrayEquals(new String[]{"1one", "2two", "3three"}, map.listKeys());
    }

    @Test
    void removeOneKeyReturnsTrue(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertTrue(map.removeOneByKey("1one"));

        assertFalse(map.isEmpty());
        assertEquals(2, map.size());
        assertArrayEquals(new Integer[]{2, 3}, map.listValues());
        assertArrayEquals(new String[]{"2two", "3three"}, map.listKeys());
    }

    @Test
    void removeAllKeys(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        map.removeOneByKey("1one");
        map.removeOneByKey("2two");
        map.removeOneByKey("3three");


        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertArrayEquals(new Integer[]{}, map.listValues());
        assertArrayEquals(new String[]{}, map.listKeys());
    }

}