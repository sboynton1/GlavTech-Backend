package tech.GlavTech.SD2022.repo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.GlavTech.SD2022.exception.FoodNotFoundException;
import tech.GlavTech.SD2022.model.Food;


@SpringBootTest
public class FoodRepoTest {

    @Autowired
    private FoodRepo foodRepo;

    @Test
    void findFoodByDescriptionTest() {
        Food result = foodRepo.findFirstByDescription("TACOS").orElseThrow(()->new FoodNotFoundException("No food found"));
        System.out.println("result = " + result);
    }

}
