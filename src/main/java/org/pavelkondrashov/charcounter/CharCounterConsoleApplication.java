package org.pavelkondrashov.charcounter;

import org.pavelkondrashov.charcounter.domain.CacheProvider;
import org.pavelkondrashov.charcounter.domain.CacheProviderImpl;
import org.pavelkondrashov.charcounter.provider.CounterProvider;
import org.pavelkondrashov.charcounter.provider.CounterProviderImpl;
import org.pavelkondrashov.charcounter.provider.CounterViewProvider;
import org.pavelkondrashov.charcounter.provider.CounterViewProviderImpl;
import org.pavelkondrashov.charcounter.validator.Validator;
import org.pavelkondrashov.charcounter.validator.ValidatorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharCounterConsoleApplication {
    public static void main(String[] args) {
        Validator validator = new ValidatorImpl();
        CacheProvider cacheProvider = new CacheProviderImpl(200);
        CounterProvider counterProvider = new CounterProviderImpl();
        CounterViewProvider counterViewProvider = new CounterViewProviderImpl();
        CharCounter charCounter = new CharCounter(validator, cacheProvider, counterProvider, counterViewProvider);

        try(BufferedReader inputText = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter a text: ");
            String text = inputText.readLine();
            System.out.println(charCounter.provideCharacterCounter(text));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
