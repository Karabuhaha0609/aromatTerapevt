package ru.aromat.aromatTerapevt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aromat.aromatTerapevt.models.Molecula;

@Repository
public interface MoleculaRepository extends JpaRepository<Molecula, Long> {
}

