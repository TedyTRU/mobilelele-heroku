package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {

    private final BrandService brandService;
    private final ModelService modelService;
    private final OfferService offerService;
    private final UserService userService;
    private final UserRoleService userRoleService;

    public DataBaseInit(BrandService brandService, ModelService modelService, OfferService offerService, UserService userService, UserRoleService userRoleService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.offerService = offerService;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) throws Exception {
        brandService.initBrands();
        modelService.initModels();
        userRoleService.initRoles();
        userService.initUsers();
        offerService.initOffers();
    }
}
