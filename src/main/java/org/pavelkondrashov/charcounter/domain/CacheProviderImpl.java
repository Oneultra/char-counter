package org.pavelkondrashov.charcounter.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheProviderImpl implements CacheProvider {
    private final int capacity;
    private final Map<String, Map<Character, Integer>> cache;

    public CacheProviderImpl(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<String, Map<Character, Integer>>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Map<Character, Integer>> eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public void put(String text, Map<Character, Integer> charCounter) {
        cache.put(text, charCounter);
    }

    @Override
    public Map<Character, Integer> getCharCounter(String text) {
        return cache.get(text);
    }

    @Override
    public boolean isMapContains(String text) {
        return cache.containsKey(text);
    }
}
