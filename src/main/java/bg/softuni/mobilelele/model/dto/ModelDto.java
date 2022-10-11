package bg.softuni.mobilelele.model.dto;

public class ModelDto {

    private Long id;
    private String name;

    public ModelDto() {
    }

    public Long getId() {
        return id;
    }

    public ModelDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelDto setName(String name) {
        this.name = name;
        return this;
    }
}
