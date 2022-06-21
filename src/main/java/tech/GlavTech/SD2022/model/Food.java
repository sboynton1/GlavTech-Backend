package tech.GlavTech.SD2022.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "food_data")
public class Food {

    @Id
    private long fdc_id;

    private String description;

    private long search_frequency;

    public void incrementSF() {
        this.search_frequency++;
    }
}
