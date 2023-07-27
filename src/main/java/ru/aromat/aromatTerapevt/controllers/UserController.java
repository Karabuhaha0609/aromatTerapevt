package ru.aromat.aromatTerapevt.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.aromat.aromatTerapevt.models.User;
import ru.aromat.aromatTerapevt.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String getUsers(){
        return "user";
    }

    @PostMapping(value="/register")
    public RedirectView addNew(User user, RedirectAttributes redir) {
        userService.save(user);
        RedirectView  redirectView= new RedirectView("/login",true);
        redir.addFlashAttribute("message",
                "Регистрация прошла успешно!");
        return redirectView;
    }
}
