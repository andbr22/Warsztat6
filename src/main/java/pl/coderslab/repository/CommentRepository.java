package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Comment;
import pl.coderslab.model.Tweet;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{

    public List<Comment> findAllByBaseTweet_IdOrderByCreatedDesc(long id);
    public List<Comment> findAllByBaseTweet(Tweet tweet);
}
