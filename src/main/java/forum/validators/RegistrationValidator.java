package forum.validators;

import forum.entity.UserEntity;
import forum.service.CheckUserService;
import forum.service.CustomService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator{

    @Autowired
    private CheckUserService checkUserService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEntity userEntity = (UserEntity) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Name field is empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty", "Last name field is empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty", "Last name field is empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Last name field is empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Last name field is empty.");
        if ((userEntity.getName().length()) > 16) {
            errors.rejectValue("name", "name.tooLong", "Name must be a less 16 characters. ");
        }
        if ((userEntity.getPassword().length()) < 4) {
            errors.rejectValue("password", "password.tooShort", "Password mustn't be a less 4 character.");
        }
        if ((userEntity.getLastName().length()) > 16) {
            errors.rejectValue("lastName", "lastName.tooShort", "Last name must be a less 16 characters.");
        }
        if (!EmailValidator.getInstance().isValid(userEntity.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Email address is not valid.");
        }
        if (checkUserService.checkLogin(userEntity.getLogin())) {
            errors.rejectValue("login", "login.alreadyExist", "This login already exist.");
        }
        if (checkUserService.checkEmail(userEntity.getEmail())) {
            errors.rejectValue("email", "email.alreadyUse", "This email address already use.");
        }
    }
}
