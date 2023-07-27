package ru.aromat.aromatTerapevt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aromat.aromatTerapevt.models.Gruppa;
import ru.aromat.aromatTerapevt.service.GruppaService;

import java.util.List;

@Controller
public class CatalogController {

        @Autowired
        private GruppaService gruppaService;


        @GetMapping("/catalog")
        public String getAllMaslo(Model model){
          List<Gruppa> grupps = gruppaService.getAllGrupps();
            model.addAttribute("grupps", grupps);
          return "categories/catalog";
    }


}
