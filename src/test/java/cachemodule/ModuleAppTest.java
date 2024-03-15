package test.java.cachemodule;

import org.junit.jupiter.api.Test;
import main.java.cachemodule.ModuleApp;
import main.java.cachemodule.EvictionStrategy;

class ModuleAppTest {

    @Test
    void testCacheOperations() {
        ModuleApp.Cache<Integer, String> cache = new ModuleApp.Cache<>(2, EvictionStrategy.LEAST_RECENTLY_USED);
        cache.put(1, "One");
        cache.put(2, "Two");
        
        assertEquals("One", cache.get(1));
        assertNull(cache.get(3)); // Key 3 should not exist
        
        cache.put(3, "Three"); // Evicts key 2
        assertNull(cache.get(2)); // Key 2 should be evicted
        assertEquals("Three", cache.get(3));
    }
}