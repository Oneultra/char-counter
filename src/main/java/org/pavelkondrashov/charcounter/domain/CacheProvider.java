package org.pavelkondrashov.charcounter.domain;

import java.util.Map;

public interface CacheProvider {
    void put(String text, Map<Character, Integer> charCounter);

    Map<Character, Integer> getCharCounter(String text);

    boolean isMapContains(String text);
}
