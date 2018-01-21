package pl.coderslab.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Tweet;
import pl.coderslab.model.User;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String index(HttpServletRequest request, Model model){
        //Session Checking
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/login";
        }
        //Other
        List<Tweet> tweets = tweetRepository.findAllByUser(user);
        model.addAttribute("tweets",tweets);
        Tweet newTweet = new Tweet();
        model.addAttribute("tweet", newTweet);
        return "home/index";
    }

    @PostMapping("")
    public String addTweet(HttpServletRequest request, @Valid Tweet tweet, BindingResult result, Model model){
        //Session Checking
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/login";
        }
        //Other
        if(result.hasErrors()){
            List<Tweet> tweets = tweetRepository.findAllByUser(user);
            model.addAttribute("tweets",tweets);
            return "home/index";
        }
        //first Save then add to model, after that new tweet
        tweet.setCreated(LocalDateTime.now());
        tweet.setUser(user);
        tweetRepository.save(tweet);

        List<Tweet> tweets = tweetRepository.findAllByUser(user);
        model.addAttribute("tweets",tweets);

        Tweet newTweet = new Tweet();
        newTweet.setUser(user);
        model.addAttribute("tweet", newTweet);
        return "home/index";
    }

    @GetMapping("/{id}")
    public String idTweets(HttpServletRequest request, @PathVariable("id") long id, Model model){
        //Session Checking
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/login";
        }
        User otherUser = userRepository.findOne(id);
        if(id == user.getId() || otherUser==null){
            return "redirect:/";
        }
        List<Tweet> tweets = tweetRepository.findAllByUserId(id);
        model.addAttribute("tweets",tweets);
        model.addAttribute("otherUser",user);
        return "home/userTweets";
    }

}
