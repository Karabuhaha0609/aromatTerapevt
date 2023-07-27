package ru.aromat.aromatTerapevt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aromat.aromatTerapevt.models.Gruppa;

import java.util.List;

@Repository
public interface GruppaRepo extends JpaRepository<Gruppa, Long> {

    List<Gruppa> findAll();
    @Query(
            value = "SELECT * FROM aromat.maslo s where s.maslo_id = :masloId",
            nativeQuery = true
    )
    List<Gruppa> findAllByMasloId(@Param("masloId") Long masloId);
}
