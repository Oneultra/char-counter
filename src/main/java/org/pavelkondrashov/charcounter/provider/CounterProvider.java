package org.pavelkondrashov.charcounter.provider;

import java.util.Map;

public interface CounterProvider {
    Map<Character, Integer> countCharacters(String text);
}
