package ru.aromat.aromatTerapevt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aromat.aromatTerapevt.models.Maslo;
import ru.aromat.aromatTerapevt.models.Shablon;
import ru.aromat.aromatTerapevt.models.User;
import ru.aromat.aromatTerapevt.repo.MasloRepo;
import ru.aromat.aromatTerapevt.repo.ShablonRepo;
import ru.aromat.aromatTerapevt.repo.UserRepo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShablonService {

    @Autowired
    private final ShablonRepo shablonRepo;
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final MasloRepo masloRepo;

    @Transactional(readOnly = true)
    public List<Shablon> getAllShablons(){
        return shablonRepo.findAll();
    }

    public void saveShablon(Principal principal, Shablon shablon){
        shablon.setUser(getUserByPrincipal(principal));
        shablonRepo.save(shablon);
    }

    public Optional<Maslo> getMasloById(Long id) {
        return masloRepo.findById(id);
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }

    public void deleteShablon(Long id){
        shablonRepo.deleteById(id);
    }

    public Shablon getShablonById(Long id) {
        return  shablonRepo.findById(id).orElse(null);
    }

    public Shablon getByShablonId(Long shablonId) {
        return shablonRepo.getReferenceById(shablonId);
    }

    public List<Shablon> getShablonByUser(Long id) {
        List<Shablon> shablons = new ArrayList<>();
        shablonRepo.findByUserId(id)
                .forEach(shablons::add);
        return shablons;
    }
}
