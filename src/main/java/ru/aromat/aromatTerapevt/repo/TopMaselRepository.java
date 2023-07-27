package ru.aromat.aromatTerapevt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aromat.aromatTerapevt.models.Client;
import ru.aromat.aromatTerapevt.models.TopMasel;

import java.util.List;

@Repository
public interface TopMaselRepository extends JpaRepository<TopMasel, Long> {
    List<TopMasel> findByClient(Client client);
    TopMasel findOneByClient(Client client);
//    TopMasel save(TopMasel topMasel, Client client);
}
