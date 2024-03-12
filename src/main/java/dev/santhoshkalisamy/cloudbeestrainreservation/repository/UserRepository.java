package dev.santhoshkalisamy.cloudbeestrainreservation.repository;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {
    public UserDetails findUserByEmail(String email);
}
