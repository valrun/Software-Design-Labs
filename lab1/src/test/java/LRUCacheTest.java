import org.junit.Test;
import static org.junit.Assert.*;
import static org.testng.Assert.assertThrows;

/**
 * @author vryndina
 */

public class LRUCacheTest {
    private static final int hashSize = 10;

    @Test
    public void initializationTest() {
        assertThrows(AssertionError.class, () -> getLRUCache(-2));
        assertThrows(AssertionError.class, () -> getLRUCache(0));
    }

    @Test
    public void isEmptyNewLRUCacheTest() {
        final var lRUCache = getLRUCache(hashSize);
        assertTrue(lRUCache.isEmpty());
    }

    @Test
    public void isNotEmptyTest() {
        final var lRUCache = getLRUCache(hashSize);
        lRUCache.put(1, 1);
        assertFalse(lRUCache.isEmpty());
    }

    @Test
    public void isEmptyTest() {
        final Integer key = 1;
        final var lRUCache = getLRUCache(hashSize);

        lRUCache.put(key, 2);
        lRUCache.put(key, 3);
        lRUCache.remove(key);

        assertTrue(lRUCache.isEmpty());
    }

    @Test
    public void containsKeyTest() {
        final Integer key = 1;
        final Integer notKey = 2;
        final var lRUCache = getLRUCache(hashSize);

        lRUCache.put(key, 3);

        assertTrue(lRUCache.containsKey(key));
        assertFalse(lRUCache.containsKey(notKey));
        assertThrows(AssertionError.class, () -> lRUCache.containsKey(null));
    }

    @Test
    public void containsValueTest() {
        final Integer key = 1;
        final Integer value = 2;
        final Integer notValue = 3;
        final var lRUCache = getLRUCache(hashSize);

        lRUCache.put(key, notValue);
        lRUCache.put(key, value);

        assertTrue(lRUCache.containsValue(value));
        assertFalse(lRUCache.containsValue(notValue));
        assertThrows(AssertionError.class, () -> lRUCache.containsValue(null));
    }

    @Test
    public void getEmptyLRUCacheTest() {
        final var lRUCache = getLRUCache(hashSize);
        assertThrows(AssertionError.class, () -> lRUCache.get(1));
    }

    @Test
    public void getTest() {
        final Integer key = 1;
        final Integer notKey = 2;
        final Integer value1 = 3;
        final Integer value2 = 4;
        final var lRUCache = getLRUCache(hashSize);

        lRUCache.put(key, value1);
        lRUCache.put(key, value2);

        assertEquals(value2, lRUCache.get(key));
        assertThrows(AssertionError.class, () -> lRUCache.get(notKey));
        assertThrows(AssertionError.class, () -> lRUCache.get(null));
    }

    @Test
    public void putTest() {
        final Integer key = 1;
        final Integer value1 = 2;
        final Integer value2 = 3;
        final var lRUCache = getLRUCache(hashSize);

        assertNull(lRUCache.put(key, value1));
        assertEquals(value1, lRUCache.put(key, value2));
        assertEquals(value2, lRUCache.get(key));

        assertThrows(AssertionError.class, () -> lRUCache.put(null, value1));
        assertThrows(AssertionError.class, () -> lRUCache.put(key, null));
    }

    @Test
    public void removeEmptyLRUCacheTest() {
        var lRUCache = getLRUCache(hashSize);
        assertThrows(AssertionError.class, () -> lRUCache.remove(1));
    }

    @Test
    public void removeTest() {
        final Integer key1 = 1;
        final Integer key2 = 2;
        final Integer value1 = 3;
        final Integer value2 = 4;
        final var lRUCache = getLRUCache(hashSize);

        lRUCache.put(key1, value1);
        lRUCache.put(key2, value2);

        assertEquals(value1, lRUCache.remove(key1));
        assertEquals(1, lRUCache.size());
        assertEquals(value2, lRUCache.remove(key2));
        assertEquals(0, lRUCache.size());

        assertThrows(AssertionError.class, () -> lRUCache.remove(null));
        assertThrows(AssertionError.class, () -> lRUCache.remove(key1));
    }

    @Test
    public void clearTest() {
        final Integer key1 = 1;
        final Integer key2 = 2;
        final Integer value1 = 3;
        final Integer value2 = 4;
        final var lRUCache = getLRUCache(hashSize);

        lRUCache.put(key1, value1);
        lRUCache.put(key2, value2);

        lRUCache.clear();
        assertEquals(0, lRUCache.size());
    }

    @Test
    public void sizeTest() {
        final int halfHashSize = hashSize / 2;
        final var lRUCache = getLRUCache(hashSize);

        for (int i = 0; i < halfHashSize; i++) {
            lRUCache.put(i, i);
            assertEquals(i + 1, lRUCache.size());
        }

        for (int i = 0; i < halfHashSize; i++) {
            assertEquals(Integer.valueOf(i), lRUCache.put(i, i * 2));
            assertEquals(halfHashSize, lRUCache.size());
        }

        for (int i = halfHashSize; i < hashSize; i++) {
            lRUCache.put(i, i);
            assertEquals(i + 1, lRUCache.size());
        }

        for (int i = hashSize; i < 2 * hashSize; i++) {
            lRUCache.put(i, i);
            assertEquals(hashSize, lRUCache.size());
        }
    }

    private LRUCache<Integer, Integer> getLRUCache(int hashSize) {
        return new LRUCache<>(hashSize);
    }
}
