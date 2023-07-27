package ru.aromat.aromatTerapevt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aromat.aromatTerapevt.models.*;
import ru.aromat.aromatTerapevt.service.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ShablonService shablonService;

    @Autowired
    private MasloService masloService;

    @Autowired
    private GruppaService gruppaService;

    @Autowired
    private VizitService vizitService;

    @Autowired
    private TopMaselService topMaselService;

    @Autowired
    private TaskService taskService;

        @GetMapping("/clients")
    public String getAllClient(Model model, String keyword, Principal principal){
        Long users = clientService.getUserByPrincipal(principal).getId();
        String userName = clientService.getUserByPrincipal(principal).getUsername();
      List<Client> clients = clientService.getClientsByUser(users);
        model.addAttribute("clientService", clientService);
      model.addAttribute("clients", clients);
        model.addAttribute("today", new Date());
        model.addAttribute("user", userName);
        //        clients = keyword == null? clientService.getAllClient():clientService.findByKeyword(keyword);
      return "clients/clientList";
    }

    @GetMapping("/clientNew")
    public String newClient(Model model, Principal principal){

        model.addAttribute("client", new Client(principal));
        Long users = shablonService.getUserByPrincipal(principal).getId();
        List<Shablon> shablons = shablonService.getShablonByUser(users);
        model.addAttribute("shablons", shablons);
        return "clients/newClient";
    }

    @PostMapping("/clientNew")
    public String saveNewClient(Client client, Principal principal) {
        clientService.saveClient(principal, client);
        return "redirect:/clients";
    }

    @RequestMapping(value = "/clients/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return "redirect:/clients";
    }

    @GetMapping("/clientEdit/{id}")
    public String ClientEdit(@PathVariable Long id, Model model, Principal principal){
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        Long users = shablonService.getUserByPrincipal(principal).getId();
        List<Shablon> shablons = shablonService.getShablonByUser(users);
        model.addAttribute("shablons", shablons);
        return "clients/clientEdit";
    }

    @RequestMapping(value = "/clients/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editClient(Client client, Principal principal){
        clientService.saveClient(principal, client);
        return "redirect:/clients";
    }
//_______________________________________________________________________________
@GetMapping("/clientKartochka/{id}")
public String getClientKartochka(Model model, @PathVariable Long id, Principal principal) {
    Client client = clientService.getClientById(id);
    model.addAttribute("client", client);


    Long users = shablonService.getUserByPrincipal(principal).getId();
    Shablon shablon = client.getShablon();
    List<Maslo> masloListShab = shablon.getMaslos();

    Map<Gruppa, List<Maslo>> groupedMaslos = masloListShab.stream()
            .collect(Collectors.groupingBy(Maslo::getGruppa));
    model.addAttribute("groupedMaslos", groupedMaslos);

    List<Vizit> vizits = client.getVizits().stream()
            .sorted(Comparator.comparing(Vizit::getDataVstrech, Comparator.reverseOrder()))
            .limit(5)
            .sorted(Comparator.comparing(Vizit::getDataVstrech))
            .collect(Collectors.toList());


    Map<Long, Map<Long, String>> reakciaMap = new HashMap<>();

    for (Vizit vizit : client.getVizits()) {
        Map<Maslo, String> reakciaMaslo = vizit.getReakciaMaslo();
        for (Maslo maslo : masloListShab) {
            if (!reakciaMap.containsKey(maslo.getId())) {
                reakciaMap.put(maslo.getId(), new HashMap<>());
            }
            if (!reakciaMaslo.containsKey(maslo)) {
                reakciaMaslo.put(maslo, "");
            }
            reakciaMap.get(maslo.getId()).put(vizit.getId(), reakciaMaslo.get(maslo));
        }
    }
    model.addAttribute("masloListShab", masloListShab);

    model.addAttribute("clientService", clientService);
    model.addAttribute("vizits", vizits);
    model.addAttribute("reakciaMap", reakciaMap);
//___________________________________________________________________
    model.addAttribute("masloService", masloService);

    List<Maslo> topMasels = client.getTopMasels();
    model.addAttribute("topMasels", topMasels);
//____________________________________________________________________

    List<Task> upcomingTasks = taskService.getAllTasksByUser(id).get("upcomingTasks");
    List<Task> clientUpcomingTasks = upcomingTasks.stream()
            .filter(task -> task.getClient().getId().equals(id))
            .collect(Collectors.toList());

    model.addAttribute("clientUpcomingTasks", clientUpcomingTasks);

    return "clients/clientKartochka";
}


//______________________________________________________________________________
@PostMapping("/clientKartochka/{id}/deleteTopMaslo/{masloId}")
public String deleteTopMaslo(@PathVariable Long id, @PathVariable Long masloId, Principal principal) {
    Client client = clientService.getClientById(id);
    List<Maslo> topMasels = client.getTopMasels();
    topMasels.removeIf(maslo -> maslo.getId().equals(masloId));
    clientService.saveClient(principal, client);
    return "redirect:/clientKartochka/{id}";
}

    @GetMapping("/clientKartochka/{id}/addMaslo")
    public String showAddMasloForm(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id);
        List<Maslo> allMaslos = masloService.getAllMaslo();
        Map<Gruppa, List<Maslo>> groupedMaslos = allMaslos.stream()
                .collect(Collectors.groupingBy(Maslo::getGruppa));
        model.addAttribute("client", client);
        model.addAttribute("allMaslos", groupedMaslos);
        return "topmasel/addTopMaslo";
    }

    @PostMapping("/clientKartochka/{id}/addMaslo")
    public String addMasloToClient(@PathVariable Long id, @RequestParam(value = "topMasels", required = false) List<Long> topMasels, Principal principal) {
        if (topMasels != null) {
            Client client = clientService.getClientById(id);
            List<Maslo> masloList = new ArrayList<>();
            for (Long masloId : topMasels) {
                Maslo maslo = masloService.getMasloById(masloId);
                masloList.add(maslo);
            }
            client.setTopMasels(masloList);
            clientService.saveClient(principal, client);
        }
        return "redirect:/clientKartochka/" + id;
    }

//______________________________________________________________________________

    @GetMapping("/clientEditShablon/{id}")
    public String clientEditShablon(@PathVariable Long id, Model model, Principal principal){
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        Long users = shablonService.getUserByPrincipal(principal).getId();
        List<Shablon> shablons = shablonService.getShablonByUser(users);
        model.addAttribute("shablons", shablons);
        return "clients/clientEditShablon";
    }
    @RequestMapping(value = "/clients/updateShab/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editclientEditShablon(Client client, Principal principal){
        clientService.saveClient(principal, client);
        return "redirect:/clientKartochka/{id}";
    }
}
