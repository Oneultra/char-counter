package org.pavelkondrashov.charcounter.provider;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class CounterViewProviderImplTest {
    private final CounterViewProvider counterViewProvider = new CounterViewProviderImpl();

    @Test
    void provideViewShouldReturnStringWhenGetAllParameters() {
        Map<Character, Integer> charactersToCount = new LinkedHashMap<>();
        charactersToCount.put('h', 1);
        charactersToCount.put('e', 2);
        charactersToCount.put('l', 4);
        charactersToCount.put('o', 2);
        charactersToCount.put(' ', 2);
        charactersToCount.put('b', 1);
        charactersToCount.put('a', 1);
        charactersToCount.put('u', 2);
        charactersToCount.put('t', 1);
        charactersToCount.put('i', 1);
        charactersToCount.put('f', 1);
        charactersToCount.put('w', 1);
        charactersToCount.put('r', 1);
        charactersToCount.put('d', 1);

        String expected = "\"h\" - 1\r\n" +
                "\"e\" - 2\r\n" +
                "\"l\" - 4\r\n" +
                "\"o\" - 2\r\n" +
                "\" \" - 2\r\n" +
                "\"b\" - 1\r\n" +
                "\"a\" - 1\r\n" +
                "\"u\" - 2\r\n" +
                "\"t\" - 1\r\n" +
                "\"i\" - 1\r\n" +
                "\"f\" - 1\r\n" +
                "\"w\" - 1\r\n" +
                "\"r\" - 1\r\n" +
                "\"d\" - 1\r\n";
        String actual = counterViewProvider.provideView(charactersToCount);

        assertThat(actual, is(equalTo(expected)));
    }
}
