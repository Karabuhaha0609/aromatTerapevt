package ru.aromat.aromatTerapevt.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ru.aromat.aromatTerapevt.models.Client;
import ru.aromat.aromatTerapevt.models.Maslo;
import ru.aromat.aromatTerapevt.models.TopMasel;
import ru.aromat.aromatTerapevt.repo.MasloRepo;
import ru.aromat.aromatTerapevt.repo.TopMaselRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopMaselService {


    private final TopMaselRepository topMaselRepository;
    private final MasloRepo masloRepo;
    private final MasloService masloService;

    private final ClientService clientService;


    public TopMaselService(TopMaselRepository topMaselRepository, MasloRepo masloRepo, MasloService masloService, ClientService clientService) {
        this.topMaselRepository = topMaselRepository;
        this.masloRepo = masloRepo;
        this.masloService = masloService;
        this.clientService = clientService;
    }

    public void saveTopMasel(TopMasel topMasel) {
        topMaselRepository.save(topMasel);
    }

    public TopMasel getTopMaselById(Long id) {
        return topMaselRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TopMasel not found"));
    }
    public List<Maslo> findByKeyword(String keyword){
        return masloRepo.findByKeyword(keyword);
    }
    public List<TopMasel> getAllTopMasels() {
        return topMaselRepository.findAll();
    }

    public List<TopMasel> getTopMaselsByClient(Client client) {
        return topMaselRepository.findByClient(client);
    }
    public TopMasel getOneTopMaselsByClient(Client client) {
        return topMaselRepository.findOneByClient(client);
    }

    public void deleteTopMaselById(Long id) {
        topMaselRepository.deleteById(id);
    }



}
