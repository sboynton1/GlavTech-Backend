package tech.GlavTech.SD2022.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.GlavTech.SD2022.exception.FoodNotFoundException;
import tech.GlavTech.SD2022.model.Food;
import tech.GlavTech.SD2022.service.FoodService;


@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {this.foodService = foodService;}

    @GetMapping("/{description}")
    public ResponseEntity<Object> findFood(@PathVariable("description") String description) {
        Food result;
        try {
            result = foodService.findFood(description);
        } catch (FoodNotFoundException e) {
            return new ResponseEntity<>("Can't find food", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
