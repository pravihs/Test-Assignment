package main.java.cachemodule;

// Cache eviction strategies
public enum EvictionStrategy {
    LEAST_RECENTLY_USED,
    LEAST_FREQUENTLY_USED,
    TIME_BASED_EXPIRATION
}