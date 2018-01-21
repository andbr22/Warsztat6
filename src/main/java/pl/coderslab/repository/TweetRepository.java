package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Tweet;
import pl.coderslab.model.User;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Long>{

    public List<Tweet> findAllByUser(User user);
    public List<Tweet> findAllByUserId(long id);
}
