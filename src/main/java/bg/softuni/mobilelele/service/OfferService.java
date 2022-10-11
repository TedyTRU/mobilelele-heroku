package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.offer.AddOfferDto;
import bg.softuni.mobilelele.model.dto.offer.OfferDetailDto;
import bg.softuni.mobilelele.model.dto.offer.SearchOfferDto;
import bg.softuni.mobilelele.model.entity.Model;
import bg.softuni.mobilelele.model.entity.Offer;
import bg.softuni.mobilelele.model.entity.User;
import bg.softuni.mobilelele.model.enums.EngineEnum;
import bg.softuni.mobilelele.model.enums.RoleEnum;
import bg.softuni.mobilelele.model.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.mapper.OfferMapper;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.repository.OfferSpecification;
import bg.softuni.mobilelele.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, UserRepository userRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
    }

    public Page<OfferDetailDto> getAllOffers(Pageable pageable) {
        return offerRepository
                .findAll(pageable)
                .map(offerMapper::offerToOfferDto);
    }

//    public List<OfferDetailDto> findOfferByOfferName(String query) {
//        return offerRepository
//                .findAllByModel_Name(query)
//                .stream()
//                .map(offerMapper::offerToOfferDto)
//                .toList();
//    }

    public void adOffer(AddOfferDto addOfferDto, UserDetails userDetails) {

        Offer newOffer = offerMapper
                .addOfferDtoToOfferEntity(addOfferDto);

        // TODO - current user should be logged in

        User seller = userRepository
                .findByEmail(userDetails.getUsername())
                .orElseThrow();

        Model model = modelRepository
                .findById(addOfferDto.getModelId())
                .orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }

    public void initOffers() {

        if (offerRepository.count() != 0) {
            return;
        }

        List<Offer> offers = new ArrayList<>();

        offers.add(new Offer()
                .setDescription("Качваш са, караш са, отиваш на фиеста 1. С Форд Фиеста.")
                .setEngine(EngineEnum.GASOLINE)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg/280px-2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg")
                .setMileage(320001)
                .setPrice(BigDecimal.valueOf(2601))
                .setTransmission(TransmissionEnum.MANUAL)
                .setYear(2005)
                .setModel(modelRepository.getById(1L))
                .setSeller(userRepository.getById(2L)));
        offers.add(new Offer()
                .setDescription("Качваш са, караш са, отиваш на фиеста 2. С Форд Фиеста.")
                .setEngine(EngineEnum.GASOLINE)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg/280px-2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg")
                .setMileage(320007)
                .setPrice(BigDecimal.valueOf(2607))
                .setTransmission(TransmissionEnum.MANUAL)
                .setYear(2005)
                .setModel(modelRepository.getById(1L))
                .setSeller(userRepository.getById(1L)));
        offers.add(new Offer()
                .setDescription("Качваш са, караш са, отиваш на фиеста 3. С Форд Фиеста.")
                .setEngine(EngineEnum.GASOLINE)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg/280px-2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg")
                .setMileage(320002)
                .setPrice(BigDecimal.valueOf(2602))
                .setTransmission(TransmissionEnum.MANUAL)
                .setYear(2005)
                .setModel(modelRepository.getById(1L))
                .setSeller(userRepository.getById(1L)));
        offers.add(new Offer()
                .setDescription("Качваш са, караш са, отиваш на фиеста 4. С Форд Фиеста.")
                .setEngine(EngineEnum.GASOLINE)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg/280px-2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg")
                .setMileage(320003)
                .setPrice(BigDecimal.valueOf(2603))
                .setTransmission(TransmissionEnum.MANUAL)
                .setYear(2005)
                .setModel(modelRepository.getById(1L))
                .setSeller(userRepository.getById(1L)));
        offers.add(new Offer()
                .setDescription("Качваш са, караш са, отиваш на фиеста 5. С Форд Фиеста.")
                .setEngine(EngineEnum.GASOLINE)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg/280px-2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg")
                .setMileage(320004)
                .setPrice(BigDecimal.valueOf(2604))
                .setTransmission(TransmissionEnum.MANUAL)
                .setYear(2005)
                .setModel(modelRepository.getById(1L))
                .setSeller(userRepository.getById(1L)));
        offers.add(new Offer()
                .setDescription("Качваш са, караш са, отиваш на фиеста 6. С Форд Фиеста.")
                .setEngine(EngineEnum.GASOLINE)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg/280px-2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg")
                .setMileage(320005)
                .setPrice(BigDecimal.valueOf(2605))
                .setTransmission(TransmissionEnum.MANUAL)
                .setYear(2005)
                .setModel(modelRepository.getById(1L))
                .setSeller(userRepository.getById(1L)));
        offers.add(new Offer()
                .setDescription("Качваш са, караш са, отиваш на фиеста 7. С Форд Фиеста.")
                .setEngine(EngineEnum.GASOLINE)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg/280px-2005_Ford_Fiesta_%28WP%29_Ghia_5-door_hatchback_%282015-07-24%29_01.jpg")
                .setMileage(320006)
                .setPrice(BigDecimal.valueOf(2606))
                .setTransmission(TransmissionEnum.MANUAL)
                .setYear(2005)
                .setModel(modelRepository.getById(1L))
                .setSeller(userRepository.getById(1L)));


        offerRepository.saveAll(offers);
    }

    public List<OfferDetailDto> searchOffer(SearchOfferDto searchOfferDto) {
        return offerRepository.findAll(new OfferSpecification(searchOfferDto))
                .stream()
                .map(offerMapper::offerToOfferDto)
                .toList();
    }

    public Optional<OfferDetailDto> getOfferDetails(Long id) {
        return offerRepository
                .findById(id)
                .map(offerMapper::offerToOfferDto);
    }

    public Optional<AddOfferDto> findOfferById(Long id) {
        return offerRepository
                .findById(id)
                .map(offerMapper::offerEntityToAddOfferDto);
    }

    public boolean isOwner(String userName, Long offerId) {

        boolean isOwner = offerRepository
                .findById(offerId)
                .filter(o -> o.getSeller().getEmail().equals(userName))
                .isPresent();

        if (isOwner) {
            return true;
        }

        boolean isAdmin = userRepository
                .findByEmail(userName)
                .filter(this::isAdmin)
                .isPresent();

        return isAdmin;
    }

    private boolean isAdmin(User user) {
        return user
                .getRole()
                .stream()
                .anyMatch(r -> r.getRole() == RoleEnum.ADMIN);
    }

    public void deleteOfferById(Long offerId) {
        offerRepository.deleteById(offerId);
    }

}
