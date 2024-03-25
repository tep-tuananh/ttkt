package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.User;

import java.util.Optional;

@Repository
public interface UserReposotory extends JpaRepository<User, Long> {
    Optional<User> findUserByUserName(String userName);

    boolean existsByUserName(String userName);
    User findUserById(Long id);
}
