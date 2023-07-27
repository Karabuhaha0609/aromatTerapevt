package ru.aromat.aromatTerapevt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aromat.aromatTerapevt.models.Shablon;
import ru.aromat.aromatTerapevt.service.GruppaService;
import ru.aromat.aromatTerapevt.service.MasloService;
import ru.aromat.aromatTerapevt.service.ShablonService;

import java.security.Principal;
import java.util.List;

@Controller
public class ShablonGruppaController {

        @Autowired
        private ShablonService shablonService;
        @Autowired
        private GruppaService gruppaService;
        @Autowired
        private MasloService masloService;


        @GetMapping("/shablons1")
        public String getAllShablons(Model model, Principal principal){
            Long users = shablonService.getUserByPrincipal(principal).getId();
            String userName = shablonService.getUserByPrincipal(principal).getUsername();
            List<Shablon> shablons = shablonService.getShablonByUser(users);
            model.addAttribute("shablons", shablons);
            model.addAttribute("user", userName);
          return "shablons/index1";
    }

    @GetMapping("/shablons2")
    public String sssss(Model model, Principal principal){
        Long users = shablonService.getUserByPrincipal(principal).getId();
        String userName = shablonService.getUserByPrincipal(principal).getUsername();
        List<Shablon> shablons = shablonService.getShablonByUser(users);
        model.addAttribute("shablons", shablons);
        model.addAttribute("user", userName);
        return "shablons/index2";
    }


}
