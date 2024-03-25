package ra.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.model.entity.Role;
import ra.model.entity.User;
import ra.repository.RoleRepository;
import ra.repository.UserReposotory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserReposotory userReposotory;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User save(User user) {

        return userReposotory.save(user);
    }


    @Override
    public User findById(Long id) {
        return userReposotory.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        userReposotory.deleteById(id);
    }

    @Override
    public User register(User user) {
        if (userReposotory.existsByUserName(user.getUserName())) {
            throw new RuntimeException("username is exists");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByName("USER"));
        User userNew = User.builder()
                .fullName(user.getFullName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(roles)
                .status(true)
                .build();
        userReposotory.save(userNew);
        return userNew;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userReposotory.findUserByUserName(userName);
    }
}
