package ra.service.user;

import ra.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUserName(String userName);
}
