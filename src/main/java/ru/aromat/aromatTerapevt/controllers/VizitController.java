package ru.aromat.aromatTerapevt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.aromat.aromatTerapevt.models.*;
import ru.aromat.aromatTerapevt.models.enams.ReakciaEnum;
import ru.aromat.aromatTerapevt.service.ClientService;
import ru.aromat.aromatTerapevt.service.MasloService;
import ru.aromat.aromatTerapevt.service.ShablonService;
import ru.aromat.aromatTerapevt.service.VizitService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class VizitController {


    private final VizitService vizitService;
    private final ClientService clientService;
    private final ShablonService shablonService;
    private final MasloService masloService;
    private static final Logger logger = LoggerFactory.getLogger(VizitController.class);
    public VizitController(VizitService vizitService, ClientService clientService, ShablonService shablonService, MasloService masloService) {
        this.vizitService = vizitService;
        this.clientService = clientService;
        this.shablonService = shablonService;
        this.masloService = masloService;
    }

    // Метод для отображения формы создания нового визита
    @GetMapping("/clientKartochka/{clientId}/vizit/new")
    public String newVizitForm(@PathVariable("clientId") Long clientId, Model model, Principal principal) {
        Client client = clientService.getClientById(clientId);
        model.addAttribute("client", client);

        Long userId = shablonService.getUserByPrincipal(principal).getId();
        Shablon shablon = client.getShablon();
        model.addAttribute("shablon", shablon);

        List<Maslo> masloList = shablon.getMaslos();

        Map<Gruppa, List<Maslo>> groupedMaslos = masloList.stream()
                .collect(Collectors.groupingBy(Maslo::getGruppa));
        model.addAttribute("groupedMaslos", groupedMaslos);

        model.addAttribute("reakciaEnumValues", ReakciaEnum.values());
        Vizit vizit = new Vizit();
        vizit.setDataVstrech(LocalDate.now());

        model.addAttribute("vizit", new Vizit());

        return "vizit/vizitNew";
    }

    @Transactional
    @PostMapping("/clientKartochka/{clientId}/vizit/new")
    public String createNewVizit(@PathVariable("clientId") Long clientId, @ModelAttribute Vizit vizit, BindingResult result) {

        Client client = clientService.getClientById(clientId);
        vizit.setClient(client);
        logger.debug("New vizit saved: {}", vizit);
        vizitService.saveVizit(vizit);

        return "redirect:/clientKartochka/{clientId}";
    }

}



