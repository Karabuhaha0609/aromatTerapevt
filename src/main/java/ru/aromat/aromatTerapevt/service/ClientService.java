package ru.aromat.aromatTerapevt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aromat.aromatTerapevt.models.Client;
import ru.aromat.aromatTerapevt.models.User;
import ru.aromat.aromatTerapevt.repo.ClientRepo;
import ru.aromat.aromatTerapevt.repo.UserRepo;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private final UserRepo userRepo;

    @Transactional(readOnly = true)
    public List<Client> getAllClient(){
        return clientRepo.findAll();
    }

    public Client saveClient(Principal principal, Client client){
        client.setUser(getUserByPrincipal(principal));
       return clientRepo.save(client);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }

    public void editClient(Long id, Client edClient, Principal principal){
          Client client = clientRepo.findById(id).orElse(null);
            client.setFirstname(edClient.getFirstname());
            client.setLastname(edClient.getLastname());
            client.setOthername(edClient.getOthername());
            client.setFullName(edClient.getFullName());
            client.setDateOfBirth(edClient.getDateOfBirth());
            client.setDateIn(edClient.getDateIn());
            client.setMobile(edClient.getMobile());
            client.setEmail(edClient.getEmail());
            client.setPhoto(edClient.getPhoto());
            client.setShablon(edClient.getShablon());

    }

    public void deleteClient(Long id){
        clientRepo.deleteById(id);
    }

    public  Client getClientById(Long id) {
        return  clientRepo.findById(id).orElse(null);
    }


//    public List<Client> findByKeyword(String keyword){
//        return clientRepo.findByKeyword(keyword);
//    }

    public List<Client> getClientsByUser(Long id) {

        List<Client> clients = new ArrayList<>();

        clientRepo.findByUserId(id)
                .forEach(clients::add);

        return clients;
    }
    public int calculateAge(Client client) {
        LocalDate birthDate = client.getDateOfBirth();
        if (birthDate == null) {
            return 0;
        }
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

}
