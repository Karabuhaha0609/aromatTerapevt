package ru.aromat.aromatTerapevt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aromat.aromatTerapevt.models.Gruppa;
import ru.aromat.aromatTerapevt.models.Maslo;
import ru.aromat.aromatTerapevt.models.Molecula;
import ru.aromat.aromatTerapevt.service.GruppaService;
import ru.aromat.aromatTerapevt.service.MasloService;
import ru.aromat.aromatTerapevt.service.MoleculaService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MasloController {

        @Autowired
        private MasloService masloService;
        @Autowired
        private GruppaService gruppaService;
    @Autowired
    private MoleculaService moleculaService;


        @GetMapping("/maslo")
        public String getAllMaslo(Model model){
            List<Maslo> masloList = masloService.getAllMaslo();

            Map<Gruppa, List<Maslo>> groupedMaslos = masloList.stream()
                    .collect(Collectors.groupingBy(Maslo::getGruppa));
            model.addAttribute("groupedMaslos", groupedMaslos);
          return "katalog/masloList";
    }

    @GetMapping("/masloNew")
    public String newMaslo(Model model){
        List<Maslo> maslo = masloService.getAllMaslo();
        model.addAttribute("maslo", maslo);
        List<Gruppa> grupps = gruppaService.getAllGrupps();
        model.addAttribute("grupps", grupps);
        return "katalog/newMaslo";
    }

    @PostMapping("/masloNew")
    public String saveNewMaslo(Maslo maslo){
        masloService.saveMaslo(maslo);
        return "redirect:/masloNew";
    }

    @RequestMapping(value = "/maslo/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteMaslo(@PathVariable Long id){
        masloService.deleteMaslo(id);
        return "redirect:/maslo";
    }

    @GetMapping("/maslo/edit/{id}")
    public String showEditMasloForm(@PathVariable("id") Long id, Model model) {
        Maslo maslo = masloService.getMasloById(id);
        List<Molecula> moleculeList = moleculaService.getAllMoleculas();
        List<Gruppa> gruppaList = gruppaService.getAllGrupps();
        model.addAttribute("maslo", maslo);
        model.addAttribute("moleculeList", moleculeList);
        model.addAttribute("gruppaList", gruppaList);
        model.addAttribute("selectedMolecules", maslo.getMolecules());
        return "katalog/masloEdit";
    }



    @RequestMapping(value = "/maslo/update/{id}", method = RequestMethod.POST)
    public String editMaslo(@PathVariable("id") Long id, @RequestParam("molecules") List<Molecula> molecules) {
        Maslo maslo = masloService.getMasloById(id);
        maslo.setMolecules(molecules);
        masloService.saveMaslo(maslo);
        return "redirect:/maslo";
    }




}
