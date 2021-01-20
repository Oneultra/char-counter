package org.pavelkondrashov.charcounter;

import org.pavelkondrashov.charcounter.domain.CacheProvider;
import org.pavelkondrashov.charcounter.provider.CounterProvider;
import org.pavelkondrashov.charcounter.provider.CounterViewProvider;
import org.pavelkondrashov.charcounter.validator.Validator;

import java.util.Map;

public class CharCounter {
    private final Validator validator;
    private final CacheProvider cacheProvider;
    private final CounterProvider counterProvider;
    private final CounterViewProvider counterViewProvider;

    public CharCounter(Validator validator, CacheProvider cacheProvider,
                       CounterProvider counterProvider, CounterViewProvider counterViewProvider) {
        this.validator = validator;
        this.cacheProvider = cacheProvider;
        this.counterProvider = counterProvider;
        this.counterViewProvider = counterViewProvider;
    }

    public String provideCharacterCounter(String text) {
        validator.validate(text);
        final Map<Character, Integer> characterToCount;

        if (cacheProvider.isMapContains(text)) {
            characterToCount = cacheProvider.getCharCounter(text);

        } else {
            characterToCount = counterProvider.countCharacters(text);
            cacheProvider.put(text, characterToCount);
        }
        return counterViewProvider.provideView(characterToCount);
    }
}
