package ru.aromat.aromatTerapevt.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aromat.aromatTerapevt.models.Maslo;
import ru.aromat.aromatTerapevt.service.MasloService;

import java.util.List;

@RestController
public class MasloRestController {

    @Autowired
    private MasloService masloService;

    @CrossOrigin
    @GetMapping("/api/maslo")
    public List<Maslo> getAllMaslo() {
        List<Maslo> maslo = masloService.getAllMaslo();
        return maslo;
    }
}
