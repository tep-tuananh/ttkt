package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {
    List<Product> findProductByProductNameLike(String productName);
}
