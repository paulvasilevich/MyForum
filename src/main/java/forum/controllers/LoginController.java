package forum.controllers;


import com.sun.istack.internal.Nullable;
import forum.dto.UserDTO;
import forum.service.CheckUserService;
import forum.validators.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private CheckUserService checkUserService;

//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    @ModelAttribute(value = "userDTO")
//    public UserDTO loginGet() {
//        return new UserDTO();
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(Model model, HttpServletResponse response, HttpServletRequest request) {
        model.addAttribute("userDTO", new UserDTO());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginGet(@ModelAttribute UserDTO userDTO, ModelAndView modelAndView) {
//        modelAndView.getModel().getOrDefault("userDTO", new UserDTO());
//        if (!model.containsAttribute("userDTO")) {
//            model.addAttribute("userDTO", new UserDTO());
//        }
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, Model model) {

        loginValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "index";
        }
        model.addAttribute("userDTO", userDTO);
        return "forum";
    }

    @Nullable
    private UserDTO createUserFromCookie(HttpServletRequest request) {
        UserDTO userDTO = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie c :
                cookies) {
            String domain = c.getDomain();
            if (domain.equals("localhost")) {
                if (userDTO == null) {
                    userDTO = new UserDTO();
                }
                String nameCookie = c.getName();
                if (nameCookie.equals("userDTO.login")) {

                    userDTO.setLogin(c.getValue());
                }
                if (nameCookie.equals("userDTO.password")) {
                    userDTO.setPassword(c.getValue());
                }

            }

        }

        return userDTO;
    }
}