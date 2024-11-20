package com.example.optimizesql;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_grants")
@Accessors(chain = true)
public class UserGrants {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_group_id")
    private UUID userGroupId;

    @BatchSize(size = 100)
//    @ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany
    @JoinTable(name = "user_grants_to_permission_group", joinColumns = @JoinColumn(name = "user_grants_id"), inverseJoinColumns = @JoinColumn(name = "user_permission_group_id"))
    List<UserPermissionGroup> userPermissionGroups = new ArrayList<>();
}
