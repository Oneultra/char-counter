package org.pavelkondrashov.charcounter.provider;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CounterProviderImpl implements  CounterProvider {
    @Override
    public Map<Character, Integer> countCharacters(String text) {
        return text.chars().mapToObj(character -> (char)character)
                .collect(Collectors.toMap(character -> character, counter -> 1, Integer::sum, LinkedHashMap::new));
    }
}
