package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends
        JpaRepository<Offer, Long>,
        JpaSpecificationExecutor<Offer> {

//    List<Offer> findAllByModel_Name(String query);

}
