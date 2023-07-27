package ru.aromat.aromatTerapevt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aromat.aromatTerapevt.models.Gruppa;
import ru.aromat.aromatTerapevt.service.GruppaService;

import java.util.List;
import java.util.Optional;

@Controller
public class GruppaController {

    @Autowired
    private GruppaService gruppaService;


    @GetMapping({"/gruppa"})
    public String getAllGrupps(Model model){
      List<Gruppa> grupps = gruppaService.getAllGrupps();
      model.addAttribute("grupps", grupps);
      return "categories/gruppaList";
    }

    @GetMapping("/newgruppa")
    public String newGruppa(Model model){
        List<Gruppa> grupps = gruppaService.getAllGrupps();
        model.addAttribute("grupps", grupps);
        return "categories/gruppaNew";
    }

    @PostMapping("/newgruppa")
    public String saveNewGruppa(Gruppa gruppa){
        gruppaService.saveGruppa(gruppa);
        return "redirect:/newgruppa";
    }


    @RequestMapping("gruppa/findById")
    @ResponseBody
    public Optional<Gruppa> getGruppaById(Long id) {
        return gruppaService.getGruppaById(id);
    }

    @RequestMapping(value = "/gruppa/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteGruppa(@PathVariable Long id){
        gruppaService.deleteGruppa(id);
        return "redirect:/newgruppa";
    }

//    @GetMapping("/categoriiEdit/{id}")
//    public String categoriiEdit(@PathVariable Long id, Model model){
//        Categorii categorii = categoriiService.getCategoriaById(id);
//        model.addAttribute("categorii", categorii);
//        return "categories/categoriiEdit";
//    }

    @RequestMapping(value = "/categorii/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editGruppa(Gruppa gruppa){
        gruppaService.saveGruppa(gruppa);
        return "redirect:/newgruppa";
    }

}
