package ra.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.entity.User;
import ra.repository.UserReposotory;
import ra.security.UserPincipal;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserReposotory userReposotory;
    public static Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPincipal userPrincipal = (UserPincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getId();
    }

    @GetMapping("/get-user")
    public String getUser(Model model){
        User user = userReposotory.findUserById(getUserId());
        model.addAttribute("user",user);
        return "auth/my-account";
    }


}
