package ru.niiar.social.customvalidator;

import ru.niiar.social.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context){
        UserDto user = (UserDto) o;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
