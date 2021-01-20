package org.pavelkondrashov.charcounter.provider;

import java.util.Map;

public interface CounterViewProvider {
    String provideView(Map<Character, Integer> charactersToCount);
}
