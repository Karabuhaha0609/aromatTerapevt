package ru.aromat.aromatTerapevt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aromat.aromatTerapevt.models.Vizit;

import java.util.List;

@Repository
public interface VizitRepository extends JpaRepository<Vizit, Long> {

    List<Vizit> findByClientId(Long clientId);

}
