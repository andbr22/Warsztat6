package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Comment;
import pl.coderslab.model.Tweet;
import pl.coderslab.model.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/{id}")
    public String comment(@PathVariable("id") long id, Model model, HttpServletRequest request){
        //Session Checking
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/login";
        }
        Tweet tweet = tweetRepository.findOne(id);
        if (tweet == null){
            return "redirect:/";
        }
        model.addAttribute("tweet",tweet);
        List<Comment> comments = commentRepository.findAllByBaseTweet_IdOrderByCreatedDesc(id);
        model.addAttribute("comments",comments);
        Comment comment = new Comment();
        model.addAttribute("comment",comment);
        return "/comment/comment";
    }

    @PostMapping("/{id}")
    public String addComment(HttpServletRequest request, @Valid Comment comment, BindingResult result, Model model, @PathVariable("id") long id){
        //Session Checking
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/login";
        }
        if(result.hasErrors()){
            return "/comment/comment";
        }
        comment.setBaseTweet(tweetRepository.findOne(id));
        comment.setUser(user);
        comment.setCreated(LocalDateTime.now());
        commentRepository.save(comment);

        return "redirect:/comment/"+id;
    }
}
