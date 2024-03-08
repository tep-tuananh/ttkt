package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Khong de trong ma code")
    private String description;
    @NotEmpty(message = "khong de trong gia san pham")
    @Min(value = 1,message = "Gia lon hon 0")
    private Double price;
    @NotEmpty(message = "Khong dc de trong")
    @Min(value = 1,message = "Hang ton hon hon 0")
    private Integer quantity;
    @NotEmpty(message = "Khong de trong ma code")
    @Size(min = 1,max = 100,message = "Toi da 100 ky tu")
    private String image;
    private Boolean status=true;
    @ManyToOne
    @JoinColumn(name = "categoryId" ,referencedColumnName = "id")
    @JsonIgnore
    private Category category;
}
