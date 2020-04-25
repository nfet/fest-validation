//package com.easytesting.validation;
//
//import org.fest.assertions.GenericAssert;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validator;
//import java.util.HashSet;
//import java.util.Set;
//
//import static java.lang.String.format;
//import static org.apache.commons.lang3.StringUtils.repeat;
//
//public class ValidatorAssert extends GenericAssert<ValidatorAssert, Set<ConstraintViolation<?>>> {
//
//    private final Class validatedType;
//    private final String propertyName;
//    private Object propertyValue;
//    private final Validator validator;
//    private final Set<ConstraintViolation<?>> constraintViolations;
//
//    protected ValidatorAssert(Validator validator, Class validatedType, String propertyName) {
//        super(ValidatorAssert.class, new HashSet<ConstraintViolation<?>>());
//        this.validatedType = validatedType;
//        this.propertyName = propertyName;
//        this.validator = validator;
//        constraintViolations = super.actual;
//        as(format("%s#%s constraint violation", validatedType.getName(), propertyName));
//    }
//
//    public ValidatorAssert withPropertyValue(Object propertyValue) {
//        return validatePropertyValue(propertyValue);
//    }
//
//    public ValidatorAssert shouldNotBeAnEmptyString() {
//        return validatePropertyValue(null).isNotValid()
//                .validatePropertyValue("").isNotValid()
//                .validatePropertyValue(" ").isNotValid();
//
//    }
//
//    public ValidatorAssert hasMinCharacterSize(int minSize) {
//        return withPropertyValue(repeat("x", minSize - 1)).isNotValid()
//                .withPropertyValue(repeat("x", minSize)).isValid();
//    }
//
//    public ValidatorAssert hasMaxCharacterSize(int maxSize) {
//        return withPropertyValue(repeat("x", maxSize + 1)).isNotValid()
//                .withPropertyValue(repeat("x", maxSize)).isValid();
//    }
//
//    public ValidatorAssert hasCharacterSize(int minSize, int maxSize) {
//        return hasMinCharacterSize(minSize)
//                .hasMaxCharacterSize(maxSize);
//    }
//
//    @SuppressWarnings("unchecked")
//    private ValidatorAssert validatePropertyValue(Object propertyValue) {
//        constraintViolations.clear();
//        Set<ConstraintViolation<?>> violations = validator.validateValue(validatedType, propertyName, propertyValue);
//        constraintViolations.addAll(violations);
//        this.propertyValue = propertyValue;
//        return this;
//    }
//
//    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
//    public ValidatorAssert isValid() {
//        if (!actual.isEmpty()) {
//            StringBuilder msg = new StringBuilder();
//            msg.append(format("%n%nConstraint violation(s) found for %s.%s:",
//                    validatedType.getSimpleName(), propertyName));
//            for (ConstraintViolation<?> constraintViolation : actual) {
//                msg.append(format("%n    message=[%s] invalid-value=[%s]",
//                        constraintViolation.getMessage(), constraintViolation.getInvalidValue()));
//            }
//            fail(msg.toString() + format("%n"));
//        }
//        return this;
//    }
//
//    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
//    public ValidatorAssert isNotValid() {
//        if (actual.isEmpty()) {
//            StringBuilder msg = new StringBuilder();
//            msg.append(format("%n%nExpected constraint violation but did not occur for %s:", validatedType.getName()));
//            msg.append(format("%n    property=[%s] value=[%s]%n", propertyName, propertyValue));
//            fail(msg.toString());
//        }
//        return this;
//    }
//
//    public static class ValidationAssertHelper {
//
//        private final Validator validator;
//        private final Class validatedType;
//
//        private ValidationAssertHelper(Validator validator, Class validatedType) {
//            this.validator = validator;
//            this.validatedType = validatedType;
//        }
//
//        public static ValidationAssertHelper newHelper(Validator validator, Class validatedType) {
//            return new ValidationAssertHelper(validator, validatedType);
//        }
//
//        public ValidatorAssert assertThat(String propertyName) {
//            return new ValidatorAssert(validator, validatedType, propertyName);
//        }
//
//    }
//}
