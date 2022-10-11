package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.Model;
import bg.softuni.mobilelele.model.enums.CategoryEnum;
import bg.softuni.mobilelele.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final BrandService brandService;

    public ModelService(ModelRepository modelRepository, BrandService brandService) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
    }

    public void initModels() {
        if (modelRepository.count() != 0) {
            return;
        }

        List<Model> models = new ArrayList<>();

        models.add(new Model()
                .setCategory(CategoryEnum.CAR)
                .setStartYear(1976)
                .setEndYear(null)
                .setName("Fiesta")
                .setBrand(brandService.findBrandByName("Ford"))
                .setImageUrl("https://static.motor.es/fotos-noticias/2021/11/precio-ford-fiesta-2022-202183060-1638099043_1.jpg"));
        models.add(new Model()
                .setCategory(CategoryEnum.CAR)
                .setStartYear(1999)
                .setEndYear(null)
                .setName("Yaris")
                .setBrand(brandService.findBrandByName("Toyota"))
                .setImageUrl("https://www.motortrend.com/uploads/sites/10/2015/11/2014-toyota-yaris-le-3-door-hatchback-angular-front.png"));
        models.add(new Model()
                .setCategory(CategoryEnum.CAR)
                .setStartYear(2012)
                .setEndYear(null)
                .setName("Sportage")
                .setBrand(brandService.findBrandByName("Kia"))
                .setImageUrl("https://hips.hearstapps.com/hmg-prod/images/18050-2023-sportage-x-pro-1635358262.jpg?crop=0.697xw:0.784xh;0.204xw,0.0721xh&resize=640:*"));
        models.add(new Model()
                .setCategory(CategoryEnum.CAR)
                .setStartYear(1968)
                .setEndYear(2000)
                .setName("Escort")
                .setBrand(brandService.findBrandByName("Ford"))
                .setImageUrl("https://www.auto-data.net/images/f110/Ford-Escort-VII-Hatch-GAL-AFL.jpg"));
        models.add(new Model()
                .setCategory(CategoryEnum.CAR)
                .setStartYear(2006)
                .setEndYear(null)
                .setName("Qashqai")
                .setBrand(brandService.findBrandByName("Nissan"))
                .setImageUrl("https://images.ams.bg/images/galleries/102682/nissan-pochva-proizvodstvo-na-avtonomen-qashqai-prez-2017-1_big.jpg"));
        models.add(new Model()
                .setCategory(CategoryEnum.CAR)
                .setStartYear(1995)
                .setEndYear(null)
                .setName("Almera")
                .setBrand(brandService.findBrandByName("Nissan"))
                .setImageUrl("https://images.autofun.ph/file1/7a172bb2ec3b426c8d163132ddd76b45_1072x604.jpg"));
        models.add(new Model()
                .setCategory(CategoryEnum.CAR)
                .setStartYear(1990)
                .setEndYear(null)
                .setName("RAV4")
                .setBrand(brandService.findBrandByName("Toyota"))
                .setImageUrl("https://www.toyota.com/imgix/content/dam/toyota/jellies/max/2022/rav4/hybridse/4524/8w2/2.png?fm=png&bg=white&w=768&h=328&q=90"));
        models.add(new Model()
                .setCategory(CategoryEnum.CAR)
                .setStartYear(1960)
                .setEndYear(null)
                .setName("Astra")
                .setBrand(brandService.findBrandByName("Opel"))
                .setImageUrl("https://www.challenges.fr/assets/img/2015/07/23/images_list-r4x3w1000-578fccc6797c7-opel-kadett-c-aero-1976-1978.jpg"));

        modelRepository.saveAll(models);
    }
}
