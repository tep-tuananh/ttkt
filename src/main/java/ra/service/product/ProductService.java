package ra.service.product;

import ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    List<Product> findByName(String name);
}
