package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1,max = 100,message = "Toi da 100 ky tu")
    private String productName;
    private String description;
    private Double price;
    private Integer quantity;
    private String image;
    private Boolean status=true;
    @ManyToOne
    @JoinColumn(name = "categoryId" ,referencedColumnName = "id")
    @JsonIgnore
    private Category category;
}
