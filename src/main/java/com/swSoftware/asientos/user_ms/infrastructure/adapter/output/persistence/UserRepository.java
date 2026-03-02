package com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence;

import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmailAndIdNot(String email,UUID id);
    boolean existsByUsernameAndIdNot(String username,UUID id);
    @Query("SELECT u FROM UserModel u WHERE (:lastId IS NULL OR u.id > :lastId) ORDER BY u.id ASC")
    List<UserModel> findNextPage(@Param("lastId") UUID lastId, Pageable pageable);
    Optional<UserModel> findByUsername(String username);

}
