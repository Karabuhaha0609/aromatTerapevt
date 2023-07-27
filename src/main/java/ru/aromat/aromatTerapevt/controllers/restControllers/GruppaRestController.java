package ru.aromat.aromatTerapevt.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aromat.aromatTerapevt.models.Gruppa;
import ru.aromat.aromatTerapevt.service.GruppaService;

import java.util.List;

@RestController
public class GruppaRestController {

    @Autowired
    private GruppaService gruppaService;

    @CrossOrigin
    @GetMapping("/api/gruppa")
    public List<Gruppa> getAllGrupps() {
        List<Gruppa> grupps = gruppaService.getAllGrupps();
        return grupps;
    }
}
