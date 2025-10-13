package org.example.repository;

import org.example.entity.User;
import org.example.service.UserProfileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByIdAndUsername(Long id, String username);

    Optional<UserProfileProjection> findByIdAndUsernameAndEmail(Long id, String username, String email);

    @Query(value = "select count(*) from service_users where group_id = 3", nativeQuery = true)
    long findPremiumGroupUsersCount();
}
