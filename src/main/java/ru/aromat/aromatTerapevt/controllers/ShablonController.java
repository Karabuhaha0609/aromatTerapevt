package ru.aromat.aromatTerapevt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.aromat.aromatTerapevt.models.Gruppa;
import ru.aromat.aromatTerapevt.models.Maslo;
import ru.aromat.aromatTerapevt.models.Shablon;
import ru.aromat.aromatTerapevt.service.GruppaService;
import ru.aromat.aromatTerapevt.service.MasloService;
import ru.aromat.aromatTerapevt.service.ShablonService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShablonController {

        @Autowired
        private ShablonService shablonService;
        @Autowired
        private GruppaService gruppaService;
        @Autowired
        private MasloService masloService;


    @GetMapping("/shablons")
        public String getAllShablons(Model model, Principal principal){
            Long users = shablonService.getUserByPrincipal(principal).getId();
            String userName = shablonService.getUserByPrincipal(principal).getUsername();
            List<Shablon> shablons = shablonService.getShablonByUser(users);
            model.addAttribute("shablons", shablons);
            model.addAttribute("user", userName);
          return "shablons/index";
    }

    @GetMapping("/shablonnew")
    public ModelAndView newMaslo(Model model, Principal principal){
        List<Gruppa> grupps = gruppaService.getAllGrupps();
        model.addAttribute("grupps", grupps);
        Shablon shablon = new Shablon(principal);
        ModelAndView mav = new ModelAndView("shablons/schablonNew");
        mav.addObject("shablon", shablon);

        List<Maslo> maslos = (List<Maslo>) masloService.getAllMaslo();

        mav.addObject("allMaslos", maslos);

        return mav;
    }

    @PostMapping("/shablonnew")
    public String saveNewShablon(Shablon shablon, Principal principal){
        shablonService.saveShablon(principal, shablon);
        return "redirect:/shablons";
    }

    @RequestMapping(value = "/shablon/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteShablon(@PathVariable Long id){
        shablonService.deleteShablon(id);
        return "redirect:/shablon";
    }

    @GetMapping("/shablonEdit/{id}")
    public String shablonEdit(@PathVariable Long id, Model model){
        Shablon shablon = shablonService.getShablonById(id);
        model.addAttribute("shablon", shablon);
        return "shablons/schablonEdit";
    }

    @RequestMapping(value = "/shablon/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editshablon(Shablon shablon, Principal principal){
        shablonService.saveShablon(principal, shablon);
        return "redirect:/shablons";
    }
    @GetMapping("/shablonid/{id}")
    public String shablonById(@PathVariable Long id, Model model){
        Shablon shablon = shablonService.getShablonById(id);
        List<Maslo> masloList = shablon.getMaslos();

        model.addAttribute("maslolist", masloList
                .stream()
                .collect(Collectors.groupingBy(Maslo::getGruppa)));

        return "shablons/shablonId";
    }

}
