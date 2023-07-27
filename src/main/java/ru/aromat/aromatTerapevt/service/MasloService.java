package ru.aromat.aromatTerapevt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aromat.aromatTerapevt.models.Maslo;
import ru.aromat.aromatTerapevt.repo.MasloRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MasloService {

    @Autowired
    private MasloRepo masloRepo;

    @Transactional
    public List<Maslo> getAllMaslo(){
        return masloRepo.findAll();
    }

    public void saveMaslo(Maslo maslo){
        masloRepo.save(maslo);
    }

    public void updateMaslo(Maslo maslo) {
        masloRepo.save(maslo);
    }

    public void deleteMaslo(Long id){
        masloRepo.deleteById(id);
    }

    public Maslo getMasloById(Long id) {
        return  masloRepo.findById(id).orElse(null);
    }

public List<Maslo> getAllMasloById (List<Long> id) {
        return masloRepo.findAllById(id);
}
    public Maslo getByMasloId(Long masloId) {
        return masloRepo.getReferenceById(masloId);
    }

    public List<Maslo> getMasloByGruppa(Long id) {
        List<Maslo> maslos = new ArrayList<>();

        masloRepo.findByGruppaId(id)
                .forEach(maslos::add);

        return maslos;
    }

}
