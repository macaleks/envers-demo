package com.example.optimizesql;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Entity
@Table(name = "user_permission_group")
@Accessors(chain = true)
public class UserPermissionGroup {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_group_id")
    private UUID userGroupId;

    @Column(name = "user_group_type")
    private String userGroupType;

}
