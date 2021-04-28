package ru.dins_сollaboration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dins_сollaboration.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user JOIN user.tokens token WHERE token = ?1")
    Optional<User> findByToken(String token);

    Optional<User> findFirstByEmail(String email);

    Optional<User> findFirstByFirNameContaining(String userName);
}
