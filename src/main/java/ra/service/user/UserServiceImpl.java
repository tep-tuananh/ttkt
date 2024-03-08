package ra.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.User;
import ra.repository.UserReposotory;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserReposotory userReposotory;

    @Override
    public Optional<User> findByUserName(String userName) {
        return userReposotory.findUserByUserName(userName);
    }
}
