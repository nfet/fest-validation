package org.easytesting.validation.example;

import com.easytesting.validation.ValidatorAssert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import static org.apache.commons.lang3.StringUtils.repeat;

public class AccountModelValidationTest {

    private ValidatorAssert.ValidationAssertHelper assertions;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        assertions = ValidatorAssert.ValidationAssertHelper
                .newHelper(validatorFactory.getValidator(), AccountModel.class);
    }

    @Test
    public void email() {
        String propertyName = "email";
        String anyValidEmail = "john@doe.com";

        assertions.assertThat(propertyName)
                .shouldNotBeAnEmptyString()
                .withPropertyValue(anyValidEmail).isValid()
                .withPropertyValue(anyValidEmail + repeat("x", 101 - anyValidEmail.length())).isNotValid();

    }

    @Test
    public void firstName() {
        String propertyName = "firstName";

        assertions.assertThat(propertyName)
                .shouldNotBeAnEmptyString()
                .hasCharacterSize(5, 20);

    }

    @Test
    public void lastName() {
        String propertyName = "lastName";

        assertions.assertThat(propertyName)
                .shouldNotBeAnEmptyString()
                .hasCharacterSize(5, 20);

    }
}
