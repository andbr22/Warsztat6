package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{
    public User findByEmail(String email);

    @Query("SELECT u from User u where u.username like %?1%")
    public List<User> findLike(String text);
}
