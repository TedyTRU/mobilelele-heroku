package bg.softuni.mobilelele.model.dto.offer;

import bg.softuni.mobilelele.model.enums.EngineEnum;
import bg.softuni.mobilelele.model.enums.TransmissionEnum;

import javax.validation.constraints.*;

public class AddOfferDto {

    private Long modelId;
    private EngineEnum engine;
    private String imageUrl;
    private TransmissionEnum transmission;
    private Integer price;
    private Integer year;
    private String description;
    private Integer mileage;

    @NotNull
    public EngineEnum getEngine() {
        return engine;
    }

    public AddOfferDto setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    @NotEmpty
    public String getImageUrl() {
        return imageUrl;
    }

    public AddOfferDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @NotNull
    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public AddOfferDto setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    @NotNull
    @Positive
    public Long getModelId() {
        return modelId;
    }

    public AddOfferDto setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    @Positive
    @NotNull
    public Integer getPrice() {
        return price;
    }

    public AddOfferDto setPrice(Integer price) {
        this.price = price;
        return this;
    }

    @NotNull
    @Min(1900)
    public Integer getYear() {
        return year;
    }

    public AddOfferDto setYear(Integer year) {
        this.year = year;
        return this;
    }

    @NotEmpty
    public String getDescription() {
        return description;
    }

    public AddOfferDto setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @Positive
    public Integer getMileage() {
        return mileage;
    }

    public AddOfferDto setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }
}
