package edai.cachedb;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testPutOneValue(){
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
    void testGetValue(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertEquals(1, map.getValue("1one"));
    }

    @Test
    void testGetThrowsIfValueDoesNotExists(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertThrows(KeyNotFoundException.class, () -> map.getValue("4four"));
    }

    @Test
    void removeOneKeyReturnsTrue(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertTrue(map.remove("1one"));

        assertFalse(map.isEmpty());
        assertEquals(2, map.size());
        assertArrayEquals(new Integer[]{2, 3}, map.listValues());
        assertArrayEquals(new String[]{"2two", "3three"}, map.listKeys());
    }

    @Test
    void removeNoKeyReturnsFalse(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        assertFalse(map.remove("4four"));

        assertFalse(map.isEmpty());
        assertEquals(3, map.size());
        assertArrayEquals(new Integer[]{1, 2, 3}, map.listValues());
        assertArrayEquals(new String[]{"1one", "2two", "3three"}, map.listKeys());
    }

    @Test
    void removeAllKeys(){
        map.put("2two", 2);
        map.put("3three", 3);
        map.put("1one", 1);

        map.remove("1one");
        map.remove("2two");
        map.remove("3three");


        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertArrayEquals(new Integer[]{}, map.listValues());
        assertArrayEquals(new String[]{}, map.listKeys());
    }

}