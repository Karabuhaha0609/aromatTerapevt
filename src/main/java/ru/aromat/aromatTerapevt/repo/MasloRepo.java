package ru.aromat.aromatTerapevt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aromat.aromatTerapevt.models.Client;
import ru.aromat.aromatTerapevt.models.Maslo;

import java.util.List;

@Repository
public interface MasloRepo extends JpaRepository<Maslo, Long> {

    List<Maslo> findByGruppaId (Long id);
    @Query(value = "select c from Client c where " +
            "c.firstname LIKE %?1% or c.lastname LIKE %?1% ")
    List<Maslo> findByKeyword(String keyword);

    List<Maslo> getMasloById(Long id);
}
