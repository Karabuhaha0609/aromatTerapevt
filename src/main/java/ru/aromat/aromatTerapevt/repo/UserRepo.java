package ru.aromat.aromatTerapevt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.aromat.aromatTerapevt.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail (String email);

    User findByUsername(String username);
}
