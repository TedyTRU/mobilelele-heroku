package bg.softuni.mobilelele.model.mapper;

import bg.softuni.mobilelele.model.dto.offer.AddOfferDto;
import bg.softuni.mobilelele.model.dto.offer.OfferDetailDto;
import bg.softuni.mobilelele.model.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    //@Mapping(target = "active", constant = "true")
    Offer addOfferDtoToOfferEntity(AddOfferDto addOfferDto);

    AddOfferDto offerEntityToAddOfferDto(Offer offer);

    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.brand.name", target = "brand")
    @Mapping(source = "seller.firstName", target = "sellerFirstName")
    @Mapping(source = "seller.lastName", target = "sellerLastName")
    OfferDetailDto offerToOfferDto(Offer offer);
}
