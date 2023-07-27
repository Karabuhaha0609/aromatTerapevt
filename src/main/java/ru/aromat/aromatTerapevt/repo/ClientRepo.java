package ru.aromat.aromatTerapevt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aromat.aromatTerapevt.models.Client;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

    @Query(value = "select c from Client c where " +
        "c.firstname LIKE %?1% or c.lastname LIKE %?1% ")
    List<Client> findByKeyword(String keyword);

    List<Client> findByUserId (Long id);
}
