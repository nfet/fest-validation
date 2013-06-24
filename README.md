fest-validation
===============

A collection of fest-based assertions for JSR-303 Validation

A Quick Example
---------------

AccountModelValidationTest.java

```java

public class AccountModelValidationTest {

    private ValidatorAssert.ValidationAssertHelper assertions;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        assertions = ValidatorAssert.ValidationAssertHelper
                .newHelper(validatorFactory.getValidator(), AccountModel.class);
    }

    @Test
    public void firstName() {
        String propertyName = "firstName";

        assertions.assertThat(propertyName)
                .withPropertyValue(null).isNotValid()
                .withPropertyValue("").isNotValid()
                .withPropertyValue(" ").isNotValid()
                .withPropertyValue(repeat("x", 4)).isNotValid()
                .withPropertyValue(repeat("x", 21)).isNotValid();

        assertions.assertThat(propertyName)
                .withPropertyValue(repeat("x", 5)).isValid()
                .withPropertyValue(repeat("x", 20)).isValid();
    }
}

```

AccountModel.java

```java
public class AccountModel {
    
    private String email;

    @NotEmpty
    @Size(min=5, max=20)
    private String firstName;

    private String lastName;

    public AccountModel() {
    }

    // getters-and-setters
}
```
