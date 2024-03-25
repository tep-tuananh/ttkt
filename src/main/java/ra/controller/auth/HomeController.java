package ra.controller.auth;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.entity.Category;
import ra.model.entity.Product;
import ra.service.category.CategoryService;
import ra.service.product.ProductService;

import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String home(HttpSession session) {
        List<Category> categories = categoryService.getAll();
        session.setAttribute("categories", categories);
        return "auth/index";
    }

}
