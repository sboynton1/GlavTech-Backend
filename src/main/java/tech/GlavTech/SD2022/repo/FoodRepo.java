package tech.GlavTech.SD2022.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.GlavTech.SD2022.model.Food;

import java.util.Optional;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {
    Optional<Food> findFirstByDescription(String description);

}
