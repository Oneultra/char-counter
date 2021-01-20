package org.pavelkondrashov.charcounter.provider;

import java.util.Map;

public class CounterViewProviderImpl implements CounterViewProvider {
    public static final String FORMAT = "\"%c\" - %d%n";

    @Override
    public String provideView(Map<Character, Integer> charactersToCount) {
        StringBuilder formattedResult = new StringBuilder();

        charactersToCount.forEach((key, value) -> formattedResult.append(String.format(FORMAT, key, value)));

        return formattedResult.toString();
    }
}
