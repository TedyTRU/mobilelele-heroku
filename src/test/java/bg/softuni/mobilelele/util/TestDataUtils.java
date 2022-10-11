package bg.softuni.mobilelele.util;

import bg.softuni.mobilelele.model.entity.*;
import bg.softuni.mobilelele.model.enums.CategoryEnum;
import bg.softuni.mobilelele.model.enums.EngineEnum;
import bg.softuni.mobilelele.model.enums.RoleEnum;
import bg.softuni.mobilelele.model.enums.TransmissionEnum;
import bg.softuni.mobilelele.repository.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TestDataUtils {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final OfferRepository offerRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;


    public TestDataUtils(UserRepository userRepository, UserRoleRepository userRoleRepository, OfferRepository offerRepository, BrandRepository brandRepository, ModelRepository modelRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.offerRepository = offerRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {

            UserRole adminRole = new UserRole().setRole(RoleEnum.ADMIN);
            UserRole userRole = new UserRole().setRole(RoleEnum.USER);

//            List<UserRole> userRoles = Arrays.stream(RoleEnum.values())
//                    .map(userRole -> new UserRole().setRole(userRole))
//                    .toList();

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    public User createTestAdmin(String email) {
        initRoles();
        User admin = new User()
                .setEmail(email)
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setActive(true)
                .setRole(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public User createTestUser(String email) {
        initRoles();
        User user = new User()
                .setEmail(email)
                .setFirstName("User")
                .setLastName("Userov")
                .setActive(true)
                .setRole(userRoleRepository
                        .findAll()
                        .stream()
                        .filter(r -> r.getRole() != RoleEnum.ADMIN)
                        .toList());

        return userRepository.save(user);
    }

    public Offer createTestOffer(User seller, Model model) {
        Offer offer = new Offer()
                .setEngine(EngineEnum.GASOLINE)
                .setMileage(10000)
                .setPrice(BigDecimal.TEN)
                .setDescription("Test description")
                .setTransmission(TransmissionEnum.MANUAL)
                .setYear(2000)
                .setModel(model)
                .setSeller(seller);

        return offerRepository.save(offer);
    }

    public Brand createBrandTest() {
        Brand brand = new Brand()
                .setName("Ford");

        return brandRepository.save(brand);
    }

    public Model createTestModel(Brand brand) {
        Model model = new Model()
                .setName("Fiesta")
                .setBrand(brand)
                .setCategory(CategoryEnum.CAR)
                .setImageUrl("https://image.com/image.png")
                .setStartYear(1978);

        return modelRepository.save(model);
    }

    public void cleanUpDatabase() {
        offerRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        modelRepository.deleteAll();
        brandRepository.deleteAll();
    }
}
