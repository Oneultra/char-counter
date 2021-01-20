package org.pavelkondrashov.charcounter.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ValidatorImplTest {
    private final Validator validator = new ValidatorImpl();

    @Test
    void validatorShouldThrowExceptionIfSentenceIsNull() {
        String text = null;

        assertThrows(IllegalArgumentException.class, () -> validator.validate(text),
                "Text is null");
    }

    @Test
    void validatorShouldThrowExceptionIfSentenceIsEmpty() {
        String text = "";

        assertThrows(IllegalArgumentException.class, () -> validator.validate(text),
                "Text is empty");
    }

    @Test
    void validatorShouldNotThrowExceptionIfSentenceNotNullOrEmpty() {
        assertDoesNotThrow(() -> validator.validate("jfie84"));
    }
}
