package ru.aromat.aromatTerapevt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aromat.aromatTerapevt.models.Vizit;
import ru.aromat.aromatTerapevt.repo.VizitRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VizitService {

    private final VizitRepository vizitRepository;
    private final Logger logger = LoggerFactory.getLogger(VizitService.class);

    public VizitService(VizitRepository vizitRepository) {
        this.vizitRepository = vizitRepository;
    }

    @Transactional
    public Vizit saveVizit(Vizit vizit) {
        return vizitRepository.save(vizit);
    }


    public List<Vizit> getAllVizits() {
        return vizitRepository.findAll();
    }

    public Optional<Vizit> getVizitById(Long id) {
        return vizitRepository.findById(id);
    }

    public void deleteVizitById(Long id) {
        vizitRepository.deleteById(id);
    }

}


