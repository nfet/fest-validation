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

```

AccountModel.java

```java
public class AccountModel {
    
    @NotEmpty
    @Email
    @Size(max = 100)
    private String email;

    @NotEmpty
    @Size(min=5, max=20)
    private String firstName;

    @NotEmpty
    @Size(min=5, max=20)
    private String lastName;

    public AccountModel() {
    }

    // getters-and-setters
}
```
