package ru.niiar.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.niiar.social.dto.UserDto;
import ru.niiar.social.exception.UserAlreadyExistsException;
import ru.niiar.social.service.UserService;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getPage(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("user") @Valid UserDto accountDto,
                                 BindingResult result, Errors errors) {
        ModelAndView modelAndView = new ModelAndView("registration", "user", accountDto);
        if (!accountDto.getPassword().equals(accountDto.getMatchingPassword()))
            modelAndView.addObject("passMatch", "Password mismatch");

        if (result.hasErrors())
            return modelAndView;
        else {
            try {
                userService.registerUser(accountDto);
            } catch (UserAlreadyExistsException e) {
                modelAndView.addObject("userExistMessage", "Error. User is already exist!");
                return modelAndView;
            }
        }
        return new ModelAndView("success","welcomemsg",
                "Congratulations! You are successfully registered to the system!");
    }
}
