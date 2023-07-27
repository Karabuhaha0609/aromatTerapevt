package ru.aromat.aromatTerapevt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aromat.aromatTerapevt.models.Shablon;

import java.util.List;

@Repository
public interface ShablonRepo extends JpaRepository<Shablon, Long> {
    List<Shablon> findByUserId (Long id);


}
