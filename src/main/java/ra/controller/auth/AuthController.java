package ra.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.service.product.ProductService;
import ra.service.user.UserService;

import java.util.List;

@Controller
@RequestMapping("")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/login")
    public String loginPage() {
        return "/auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/auth/register";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("user") User user) {
        User userNew = userService.register(user);
        return "redirect:/login";
    }
    @GetMapping("/findByName/{search}")
    public String findByProductName(@PathVariable String search,Model model) {
        List<Product> productList = productService.findByName(search);
        model.addAttribute("productList",productList);
        return "auth/index";
    }


}
