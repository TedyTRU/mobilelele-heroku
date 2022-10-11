package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.UserRole;
import bg.softuni.mobilelele.model.enums.RoleEnum;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void initRoles() {

        if (userRoleRepository.count() != 0) {
            return;
        }

        List<UserRole> userRoles = Arrays.stream(RoleEnum.values())
                .map(userRole -> new UserRole().setRole(userRole))
                .toList();

        userRoleRepository.saveAll(userRoles);
    }
}
