package tech.GlavTech.SD2022.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.GlavTech.SD2022.exception.FoodNotFoundException;
import tech.GlavTech.SD2022.model.Food;
import tech.GlavTech.SD2022.repo.FoodRepo;

import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepo foodRepo;

    @Autowired
    private FoodService(FoodRepo foodRepo) {this.foodRepo = foodRepo;}

    public Food findFood(String description) {
        Food result = foodRepo.findFirstByDescription(description).orElseThrow(()-> new FoodNotFoundException("Food with that name not found"));

        //increment search frequency and update db
        result.incrementSF();
        foodRepo.save(result);
        return result;
    }


}
