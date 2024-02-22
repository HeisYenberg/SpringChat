package com.heisyenberg.springchatserver.repositories;

import com.heisyenberg.springchatserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM users AS u WHERE u.username = :username")
    Optional<User> findUserByUsername(@Param("username") String username);
}
