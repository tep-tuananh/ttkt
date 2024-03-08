package ra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.model.entity.User;
import ra.service.user.UserService;

import java.util.Optional;


@Service
public class CustomDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByUserName(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return UserPincipal.builder()
                    .user(user).authorities(user.getRoles().stream().map(iteam -> new SimpleGrantedAuthority(iteam.getName())).toList())
                    .build();
        }
        return null;
    }
}
