package ru.aromat.aromatTerapevt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aromat.aromatTerapevt.models.User;
import ru.aromat.aromatTerapevt.repo.UserRepo;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ReakciaService {


    @Autowired
    private final UserRepo userRepo;



    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }

}
