package ra.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Product;
import ra.repository.ProductRepository;

import java.util.List;
@Service
public class ProductServiceImpl  implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findProductByProductNameLike(name);
    }
}
