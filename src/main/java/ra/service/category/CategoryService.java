package ra.service.category;

import ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(Long id);
}
