package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.exception.ObjectNotFoundException;
import bg.softuni.mobilelele.model.dto.offer.AddOfferDto;
import bg.softuni.mobilelele.model.dto.offer.SearchOfferDto;
import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public String allOffers(Model model,
                            @PageableDefault(sort = "price",
                                    direction = Sort.Direction.ASC,
                                    page = 0, size = 3) Pageable pageable) {

        model.addAttribute("offers", offerService.getAllOffers(pageable));

        return "offers";
    }

    @GetMapping("/add")
    public String addOffer(Model model) {

        if (!model.containsAttribute("addOfferModel")) {
            model.addAttribute("addOfferModel", new AddOfferDto());
        }

        model.addAttribute("brands", brandService.getAllBrands());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto addOfferModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);

            return "redirect:add";
        }

        offerService.adOffer(addOfferModel, userDetails);

        return "redirect:all";
    }

    @GetMapping("/search")
    public String search(@Valid SearchOfferDto searchOfferDto,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchOfferModel", searchOfferDto);
            model.addAttribute("org.springframework.validation.BindingResult.searchOfferModel", bindingResult);

            return "offer-search";
        }

        if (!model.containsAttribute("searchOfferModel")) {
            model.addAttribute("searchOfferModel", searchOfferDto);
        }

        if (!searchOfferDto.isEmpty()) {
            model.addAttribute("offers", offerService.searchOffer(searchOfferDto));
        }

        return "offer-search";
    }

    @GetMapping("/{id}")
    public String getOfferDetail(@PathVariable("id") Long id,
                                 Model model) {

        var offer = offerService.getOfferDetails(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with ID " + id + " not found!"));

        model.addAttribute("offer", offer);

        return "details";
    }

//    @PreAuthorize("@offerService.isOwner(#principal.name, #id)")
//    @DeleteMapping("/{id}")
//    public String deleteOffer(Principal principal,
//                              @PathVariable("id") Long id) {
//
//        offerService.deleteOfferById(id);
//
//        return "redirect:all";
//    }

    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable("id") Long id) {

        offerService.deleteOfferById(id);

        return "redirect:all";
    }

    @GetMapping("/{id}/edit")
    public String editOffer(@PathVariable("id") Long id,
                            Model model) {

        var offer = offerService.findOfferById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with ID " + id + " not found!"));

        model.addAttribute("offer", offer);

        return "offer-add";
    }

}
