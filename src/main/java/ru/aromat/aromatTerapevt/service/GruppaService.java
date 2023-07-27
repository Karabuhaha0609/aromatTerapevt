package ru.aromat.aromatTerapevt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aromat.aromatTerapevt.models.Gruppa;
import ru.aromat.aromatTerapevt.repo.GruppaRepo;
import ru.aromat.aromatTerapevt.repo.MasloRepo;

import java.util.List;
import java.util.Optional;

@Service
public class GruppaService {

    @Autowired
    private GruppaRepo gruppaRepo;
    @Autowired
    private MasloRepo masloRepo;

    public List<Gruppa> getAllGrupps(){
        return gruppaRepo.findAll();
    }

    public void saveGruppa(Gruppa gruppa){
        gruppaRepo.save(gruppa);
    }

    public void deleteGruppa(Long id){
        gruppaRepo.deleteById(id);
    }

    public Optional<Gruppa> getGruppaById(Long id) {
        return  gruppaRepo.findById(id);
    }

    public List<Gruppa> getGruppaByMaslo(Long masloId) {
        return gruppaRepo.findAllByMasloId(masloId);
    }


}
