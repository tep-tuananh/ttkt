package ra.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String loginPage() {
        return "/auth/login";
    }
    @PostMapping("/register")
    public String register(){
        return "/auth/register";
    }
}
