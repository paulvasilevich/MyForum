package forum.controllers;

import forum.converters.EntityConverterDto;
import forum.dto.UserDTO;
import forum.entity.ProfileEntity;
import forum.entity.UserEntity;
import forum.service.CustomService;
import forum.validators.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private CustomService<UserEntity> userEntityCustomService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registrationGet( ModelAndView modelAndView) {
        modelAndView.addObject("userEntity", new UserEntity());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@Valid @ModelAttribute UserEntity userEntity, BindingResult bindingResult,
                                   Model model, HttpServletRequest request,HttpServletResponse response) {

        registrationValidator.validate(userEntity,bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        ProfileEntity profileEntity = new ProfileEntity();
        userEntity.setProfile(profileEntity);
        userEntityCustomService.createEntity(userEntity);
        UserDTO userDTO = EntityConverterDto.convertUserToDTO(userEntity);

        createUserCookie(response, userDTO);
        model.addAttribute(userDTO);

        return "redirect:/login";
    }

    private void createUserCookie(HttpServletResponse response, UserDTO userDTO) {
        Cookie login = new Cookie("userDTO.login", userDTO.getLogin());
        Cookie password = new Cookie("userDTO.password", userDTO.getPassword());
        login.setDomain("localhost");
        password.setDomain("localhost");
        login.setMaxAge(60*60*24);
        password.setMaxAge(60*60*24);
        response.addCookie(login);
        response.addCookie(password);
    }
}
