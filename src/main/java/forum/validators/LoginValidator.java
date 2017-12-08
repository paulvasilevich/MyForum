package forum.validators;

import forum.dto.UserDTO;
import forum.service.CheckUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator{

    @Autowired
    private CheckUserService checkUserService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserDTO userDTO = (UserDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"login", "login.empty", "Please enter login.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "password.empty", "Please enter password.");

        if (!checkUserService.checkLogin(userDTO.getLogin())) {
            errors.rejectValue("login", "login.wrong", "Login is wrong.");
            return;
        }
        if (!checkUserService.checkPassword(userDTO)) {
            errors.rejectValue("password", "password.wrong", "Password is wrong.");
        }
    }


}
