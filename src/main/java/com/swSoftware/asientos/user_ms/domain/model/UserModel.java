package com.swSoftware.asientos.user_ms.domain.model;

import com.swSoftware.asientos.user_ms.domain.status.StatusUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "user_table", indexes = {
        @Index(name = "emailIndex", columnList = "email"),
        @Index(name = "nameIndex", columnList = "name")
})
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String name;
    private String lastname;
    private String password;
    @Column(unique = true)
    private String email;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private StatusUser status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleModel> roles;
    private Instant createdAt;
    private Instant deleteAt;
}
