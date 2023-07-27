package ru.aromat.aromatTerapevt.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aromat.aromatTerapevt.models.User;
import ru.aromat.aromatTerapevt.repo.UserRepo;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    final UserRepo userRepo;

    @GetMapping("/login")
    public String loginPage(){
        return "security/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "security/register";
    }

    @GetMapping("/index1")
    public String index1Page(){
        return "shablons/index1";
    }


    @GetMapping("/index")
    public String indexPage(@AuthenticationPrincipal User user, Model model){
        if (user != null){
            model.addAttribute("user", user.getUsername());
            return "index";
        }
            model.addAttribute("user", "Аноним");
            return "index";
    }
}
