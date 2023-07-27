package ru.aromat.aromatTerapevt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aromat.aromatTerapevt.models.Molecula;
import ru.aromat.aromatTerapevt.repo.MoleculaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MoleculaService {

    private final MoleculaRepository moleculaRepository;

    @Autowired
    public MoleculaService(MoleculaRepository moleculaRepository) {
        this.moleculaRepository = moleculaRepository;
    }

    public Molecula createMolecula(Molecula molecula) {
        return moleculaRepository.save(molecula);
    }

    public List<Molecula> getAllMoleculas() {
        return moleculaRepository.findAll();
    }

    public Molecula updateMolecula(Long moleculaId, Molecula newMoleculaData) {
        Molecula molecula = moleculaRepository.findById(moleculaId)
                .orElseThrow(() -> new EntityNotFoundException("Molecula not found with id: " + moleculaId));
        molecula.setName(newMoleculaData.getName());
        molecula.setMaslos(newMoleculaData.getMaslos());
        // изменяем другие поля
        return moleculaRepository.save(molecula);
    }
    public void deleteMolecula(Long moleculaId) {
        Molecula molecula = moleculaRepository.findById(moleculaId)
                .orElseThrow(() -> new EntityNotFoundException("Molecula not found with id: " + moleculaId));
        moleculaRepository.delete(molecula);
    }
    public Molecula getMoleculaById(Long moleculaId) {
        return moleculaRepository.findById(moleculaId)
                .orElseThrow(() -> new EntityNotFoundException("Molecula not found with id: " + moleculaId));
    }

}

