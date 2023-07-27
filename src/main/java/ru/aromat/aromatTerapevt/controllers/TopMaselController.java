package ru.aromat.aromatTerapevt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aromat.aromatTerapevt.service.ClientService;
import ru.aromat.aromatTerapevt.service.GruppaService;
import ru.aromat.aromatTerapevt.service.MasloService;
import ru.aromat.aromatTerapevt.service.TopMaselService;

@Controller
@RequestMapping("/clientKartochka")
public class TopMaselController {

    private final TopMaselService topMaselService;
    private final MasloService masloService;
    private final ClientService clientService;
    private final GruppaService gruppaService;



    public TopMaselController(TopMaselService topMaselService, MasloService masloService, ClientService clientService, GruppaService gruppaService) {
        this.topMaselService = topMaselService;
        this.masloService = masloService;
        this.clientService = clientService;
        this.gruppaService = gruppaService;
    }





//_________________________________________________________________________________________________________
//    @GetMapping("/{clientId}/createTopMasel")
//    public String newTopMaselForm(@PathVariable("clientId") Long id, String keyword, Model model) {
//        Client client = clientService.getClientById(id);
//        List<Maslo> masloList = masloService.getAllMaslo();
//        Map<Gruppa, List<Maslo>> groupedMaslos = masloList.stream()
//                .collect(Collectors.groupingBy(Maslo::getGruppa));
//        model.addAttribute("groupedMaslos", groupedMaslos);
//        model.addAttribute("client", client);
//        model.addAttribute("topMasel", new TopMasel());
//        return "topmasel/createTopMasel";
//    }
//
//    @PostMapping("/saveTopMasel")
//    public String saveTopMasel(@ModelAttribute TopMasel topMasel, @RequestParam("maslos") List<Long> masloIds,
//                               @RequestParam("clientId") Long clientId) {
//        List<Maslo> maslos = masloIds.stream().map(masloService::getMasloById).collect(Collectors.toList());
//        topMasel.setMaslos(maslos);
//        Client client = clientService.getClientById(clientId);
//        topMasel.setClient(client);
//        topMaselService.saveTopMasel(topMasel);
//        return "redirect:/clientKartochka/" + clientId;
//    }
//
//@GetMapping("/{clientId}/editTopMasel/{topMaselId}")
//public String editTopMaselForm(@PathVariable("clientId") Long clientId, @PathVariable("topMaselId") Long topMaselId, Model model) {
//    TopMasel topMasel = topMaselService.getTopMaselById(topMaselId);
//    Client client = clientService.getClientById(clientId);
//    List<Maslo> availableMaslos = masloService.getAllMaslo();
//    Map<Gruppa, List<Maslo>> groupedMaslos = availableMaslos.stream().collect(Collectors.groupingBy(Maslo::getGruppa));
//    model.addAttribute("groupedMaslos", groupedMaslos);
//    model.addAttribute("client", client);
//    model.addAttribute("topMasel", topMasel);
//    return "topmasel/editTopMasel";
//}
//
//    @PostMapping("/saveTopMaselChanges")
//    public String saveTopMaselChanges(@ModelAttribute TopMasel topMasel, @RequestParam("maslos") List<Long> masloIds,
//                                      @RequestParam("clientId") Long clientId) {
//        List<Maslo> maslos = masloService.getAllMasloById(masloIds);
//        topMasel.setMaslos(maslos);
//        topMaselService.saveTopMasel(topMasel);
//        return "redirect:/clientKartochka/" + clientId;
//    }




}

