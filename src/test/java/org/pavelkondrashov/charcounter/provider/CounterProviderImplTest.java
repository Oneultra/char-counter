package org.pavelkondrashov.charcounter.provider;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


class CounterProviderImplTest {
    
    private final CounterProvider counterProvider = new CounterProviderImpl();

    @Test
    void countCharactersShouldReturnMapWhenGetTextWith1Words() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('s', 1);
        expected.put('e', 3);
        expected.put('n', 2);
        expected.put('t', 1);
        expected.put('c', 1);

        Map<Character, Integer> actual = counterProvider.countCharacters("sentence");

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void countCharactersShouldReturnMapWhenGetTextWith2Words() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put(' ', 1);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);

        Map<Character, Integer> actual = counterProvider.countCharacters("hello world");

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void countCharactersShouldReturnMapWhenGetTextWith3WordsWithUniqueSymbols() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('h', 1);
        expected.put('3', 1);
        expected.put('l', 3);
        expected.put('0', 1);
        expected.put(' ', 2);
        expected.put('b', 1);
        expected.put('#', 1);
        expected.put('4', 1);
        expected.put('t', 1);
        expected.put('1', 1);
        expected.put('f', 1);
        expected.put('&', 1);
        expected.put('w', 1);
        expected.put('o', 1);
        expected.put('r', 1);
        expected.put('%', 1);
        expected.put('d', 1);
        expected.put('!', 1);

        Map<Character, Integer> actual = counterProvider.countCharacters("h3ll0 b#4t1f&l wor%d!");

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void countCharactersShouldReturnMapWhenGetTextWithOneWordWithOneRepeatedLetter() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('w', 7);

        Map<Character, Integer> actual =
                counterProvider.countCharacters("wwwwwww");

        assertThat(actual, is(expected));
    }
}
