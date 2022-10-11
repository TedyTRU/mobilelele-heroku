package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.User;
import bg.softuni.mobilelele.model.entity.UserRole;
import bg.softuni.mobilelele.model.enums.RoleEnum;
import bg.softuni.mobilelele.model.user.MobileleUserDetails;
import bg.softuni.mobilelele.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MobileleUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private MobileleUserDetailsService toTest;

    @BeforeEach
    void setUp() {

        toTest = new MobileleUserDetailsService(
                mockUserRepo
        );
    }

    @Test
    void testLoadUserByUsername_UserExists() {

        // arrange
        User testUser = new User()
                .setEmail("test@example.com")
                .setPassword("123456")
                .setFirstName("Pesho")
                .setLastName("Petrov")
                .setImageUrl("http://image.com/image")
                .setActive(true)
                .setRole(List.of(
                        new UserRole().setRole(RoleEnum.USER),
                        new UserRole().setRole(RoleEnum.ADMIN)
                ));

        when(mockUserRepo.findByEmail(testUser.getEmail()))
                .thenReturn(Optional.of(testUser));

        // act
        MobileleUserDetails userDetails = (MobileleUserDetails) toTest.loadUserByUsername(testUser.getEmail());

        // assert
        Assertions.assertEquals(testUser.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUser.getFirstName() + " " + testUser.getLastName(), userDetails.getFullName());

        var authorities = userDetails.getAuthorities();
        Assertions.assertEquals(2, authorities.size());

        var authoritiesIter = authorities.iterator();
        Assertions.assertEquals("ROLE_" + RoleEnum.USER.name(), authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + RoleEnum.ADMIN.name(), authoritiesIter.next().getAuthority());

    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

        // arrange
        // NOTE: No need to arrange anything, mocks return empty optionals.

        // act

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("not-existant@example.com"));

    }

}
