package main.java.cachemodule;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ModuleApp {
    public static class Cache<K, V> {
        private int capacity;
        private Map<K, CacheEntry<V>> cache;
        private Queue<K> evictionQueue;
        private EvictionStrategy evictionStrategy;

        public Cache(int capacity, EvictionStrategy evictionStrategy) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.evictionQueue = new LinkedList<>();
            this.evictionStrategy = evictionStrategy;
        }

        public void put(K key, V value) {
            evictIfNeeded();
            CacheEntry<V> entry = new CacheEntry<>(value);
            cache.put(key, entry);
            evictionQueue.add(key);
        }

        public V get(K key) {
            if (cache.containsKey(key)) {
                CacheEntry<V> entry = cache.get(key);
                entry = new CacheEntry<>(entry.getValue());
                cache.put(key, entry);
                return entry.getValue();
            }
            return null;
        }

        private void evictIfNeeded() {
            if (cache.size() >= capacity) {
                K keyToRemove = null;
                switch (evictionStrategy) {
                    case LEAST_RECENTLY_USED:
                        keyToRemove = evictionQueue.poll();
                        break;
                    default:
                        break;
                }
                if (keyToRemove != null) {
                    cache.remove(keyToRemove);
                }
            }
        }
    }

    private static class CacheEntry<V> {
        private long timestamp;
        private V value;

        public CacheEntry(V value) {
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }

        @SuppressWarnings("unused")
        public long getTimestamp() {
            return timestamp;
        }

        public V getValue() {
            return value;
        }
    }
}