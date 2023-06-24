package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);

    Boolean existsByDniNumber(String dniNumber);

    boolean existsByMail(String mail);

    Optional<UserEntity> findByMail(String mail);

    Optional<UserEntity> findByDniNumber(String dniNumber);

    Optional<UserEntity> findByName(String name);
}
