package com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence;

import com.swSoftware.asientos.user_ms.domain.model.RoleModel;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    @Query("SELECT u FROM RoleModel u WHERE (:lastId IS NULL OR u.id > :lastId) ORDER BY u.id ASC")
    List<RoleModel> findNextPage(@Param("lastId") Long lastId, Pageable pageable);
}
