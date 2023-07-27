package ru.aromat.aromatTerapevt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aromat.aromatTerapevt.models.Molecula;
import ru.aromat.aromatTerapevt.service.MoleculaService;

import java.util.List;

@Controller
@RequestMapping("/moleculas")
public class MoleculaController {

    private final MoleculaService moleculaService;

    @Autowired
    public MoleculaController(MoleculaService moleculaService) {
        this.moleculaService = moleculaService;
    }

    @GetMapping("")
    public String getAllMoleculas(Model model) {
        List<Molecula> moleculas = moleculaService.getAllMoleculas();
        model.addAttribute("moleculas", moleculas);
        return "moleculas/moleculasList";
    }

    @GetMapping("/new")
    public String showMoleculaForm(Model model) {
        model.addAttribute("molecula", new Molecula());
        return "moleculas/moleculasnew";
    }

    @PostMapping("/new")
    public String createMolecula(@ModelAttribute("molecula") Molecula molecula) {
        moleculaService.createMolecula(molecula);
        return "redirect:/moleculas/new";
    }

    @GetMapping("/{id}")
    public String getMoleculaById(@PathVariable("id") Long moleculaId, Model model) {
        Molecula molecula = moleculaService.getMoleculaById(moleculaId);
        model.addAttribute("molecula", molecula);
        return "moleculas/moleculasview";
    }

    @GetMapping("/{id}/edit")
    public String showMoleculaEditForm(@PathVariable("id") Long moleculaId, Model model) {
        Molecula molecula = moleculaService.getMoleculaById(moleculaId);
        model.addAttribute("molecula", molecula);
        return "moleculas/moleculasedit";
    }

    @PostMapping("/{id}/edit")
    public String updateMolecula(@PathVariable("id") Long moleculaId, @ModelAttribute("molecula") Molecula newMoleculaData) {
        moleculaService.updateMolecula(moleculaId, newMoleculaData);
        return "redirect:/moleculas";
    }

    @PostMapping("/{id}/delete")
    public String deleteMolecula(@PathVariable("id") Long moleculaId) {
        moleculaService.deleteMolecula(moleculaId);
        return "redirect:/moleculas";
    }
}

