package org.pavelkondrashov.charcounter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pavelkondrashov.charcounter.domain.CacheProvider;
import org.pavelkondrashov.charcounter.provider.CounterProvider;
import org.pavelkondrashov.charcounter.provider.CounterViewProvider;
import org.pavelkondrashov.charcounter.validator.Validator;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class CharCounterTest {
    @Mock
    private CacheProvider mockedCacheProvider;

    @Mock
    private CounterProvider mockedCounterProvider;

    @Mock
    private CounterViewProvider mockedCounterViewProvider;

    @Mock
    private Validator mockedValidator;

    @InjectMocks
    private CharCounter charCounter;

    @Test
    void provideCharacterCounterShouldReturnStringIfCacheNotHaveText() {
        String text = "hello beautiful world";

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

        String formattedView = "\"h\" - 1\r\n" +
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

        when(mockedCacheProvider.isMapContains(anyString())).thenReturn(false);
        when(mockedCounterProvider.countCharacters(anyString())).thenReturn(charactersToCount);
        when(mockedCounterViewProvider.provideView(anyMap())).thenReturn(formattedView);

        charCounter.provideCharacterCounter(text);

        verify(mockedCounterProvider).countCharacters(anyString());
        verify(mockedCacheProvider).isMapContains(anyString());
        verify(mockedValidator).validate(anyString());
        verify(mockedCounterViewProvider).provideView(anyMap());
    }

    @Test
    void provideCharacterCounterShouldReturnStringIfTextReadFromCache() {
        String text = "hello beautiful world";

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

        String formattedView = "\"h\" - 1\r\n" +
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

        when(mockedCacheProvider.isMapContains(anyString())).thenReturn(true);
        when(mockedCacheProvider.getCharCounter(anyString())).thenReturn(charactersToCount);
        when(mockedCounterViewProvider.provideView(anyMap())).thenReturn(formattedView);

        charCounter.provideCharacterCounter(text);

        verify(mockedValidator).validate(anyString());
        verify(mockedCacheProvider).isMapContains(anyString());
        verify(mockedCacheProvider).getCharCounter(anyString());
        verify(mockedCounterViewProvider).provideView(anyMap());
        verifyNoMoreInteractions(mockedCounterProvider);
    }

    @Test
    void ifValidatorThrowsIllegalArgumentExceptionProvideCharacterCounterShouldNotStarts() {
        doThrow(new IllegalArgumentException()).when(mockedValidator).validate(anyString());

        assertThrows(IllegalArgumentException.class, () -> charCounter.provideCharacterCounter(""));

        verifyNoMoreInteractions(mockedCacheProvider, mockedCounterProvider, mockedCounterViewProvider);
    }
}
