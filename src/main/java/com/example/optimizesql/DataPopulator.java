package com.example.optimizesql;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataPopulator {

    private final UserGrantsRepository userGrantsRepository;
    private final UserPermissionGroupRepository userPermissionGroupRepository;

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void populate() {
        UserPermissionGroup userPermissionGroup1 = new UserPermissionGroup();
        userPermissionGroup1.setId(UUID.randomUUID());
        userPermissionGroup1.setName("name1");
        userPermissionGroup1.setUserGroupId(UUID.randomUUID());
        userPermissionGroup1.setUserGroupType("userGroupType1");

        UserPermissionGroup userPermissionGroup2 = new UserPermissionGroup();
        userPermissionGroup2.setId(UUID.randomUUID());
        userPermissionGroup2.setName("name2");
        userPermissionGroup2.setUserGroupId(UUID.randomUUID());
        userPermissionGroup2.setUserGroupType("userGroupType2");

        UserPermissionGroup userPermissionGroup3 = new UserPermissionGroup();
        userPermissionGroup3.setId(UUID.randomUUID());
        userPermissionGroup3.setName("name3");
        userPermissionGroup3.setUserGroupId(UUID.randomUUID());
        userPermissionGroup3.setUserGroupType("userGroupType3");

        List<UserPermissionGroup> userPermissionGroups = userPermissionGroupRepository.saveAll(List.of(userPermissionGroup3, userPermissionGroup2, userPermissionGroup1));

        UserGrants userGrants1 = new UserGrants();
        userGrants1.setId(UUID.randomUUID());
        userGrants1.setUserLogin("userLogin1");
        userGrants1.setUserGroupId(UUID.randomUUID());
        userGrants1.setUserPermissionGroups(List.of(userPermissionGroups.get(0)));

        UserGrants userGrants2 = new UserGrants();
        userGrants2.setId(UUID.randomUUID());
        userGrants2.setUserLogin("userLogin2");
        userGrants2.setUserGroupId(UUID.randomUUID());
        userGrants2.setUserPermissionGroups(List.of(userPermissionGroups.get(1)));

        UserGrants userGrants3 = new UserGrants();
        userGrants3.setId(UUID.randomUUID());
        userGrants3.setUserLogin("userLogin3");
        userGrants3.setUserGroupId(UUID.randomUUID());
        userGrants3.setUserPermissionGroups(List.of(userPermissionGroups.get(2)));

        userGrantsRepository.saveAll(List.of(userGrants1, userGrants2, userGrants3));
    }
}
