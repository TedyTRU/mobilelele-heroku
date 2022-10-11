package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.dto.offer.SearchOfferDto;
import bg.softuni.mobilelele.model.entity.Offer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OfferSpecification implements Specification<Offer> {

    private final SearchOfferDto searchOfferDto;

    public OfferSpecification(SearchOfferDto searchOfferDto) {
        this.searchOfferDto = searchOfferDto;
    }

    @Override
    public Predicate toPredicate(Root<Offer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.conjunction();

        if (searchOfferDto.getModel() != null && !searchOfferDto.getModel().isEmpty()) {
            predicate.getExpressions()
                    .add(criteriaBuilder.and(criteriaBuilder
                            .equal(root.join("model").get("name"), searchOfferDto.getModel()))
                    );
        }

        if (searchOfferDto.getMinPrice() != null) {
            predicate.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder
                            .greaterThanOrEqualTo(root.get("price"), searchOfferDto.getMinPrice()))
            );
        }

        if (searchOfferDto.getMaxPrice() != null) {
            predicate.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder
                            .lessThanOrEqualTo(root.get("price"), searchOfferDto.getMaxPrice()))
            );
        }

        return predicate;
    }
}
