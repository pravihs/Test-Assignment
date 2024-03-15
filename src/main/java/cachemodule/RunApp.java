package main.java.cachemodule;

public class RunApp {
    public static void main(String[] args) {
        Cache<Integer, String> cache = new Cache<>(2, EvictionStrategy.LEAST_RECENTLY_USED);
        cache.put(1, "One");
        cache.put(2, "Two");
        System.out.println(cache.get(1));
        cache.put(3, "Three"); // Evicts key 2
        System.out.println(cache.get(2)); // Output: null
        System.out.println(cache.get(3)); // Output: Three
    }
}