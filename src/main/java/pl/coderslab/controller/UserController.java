package pl.coderslab.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.User;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String registerForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "user/register";
    }

    @PostMapping("/register")
    public  String registerUser(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "user/register";
        }else {
            userRepository.save(user);
            //SAVE TO SESSION
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String loginForm(){
        return"user/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request){
        System.out.println(email);
        System.out.println(password);
        if (email.length()>0 && password.length()>0){
            User user = userRepository.findByEmail(email);

            if(user!=null && BCrypt.checkpw(password, user.getPassword())){
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                return "redirect:/";
            }
        }
        return "user/login";
    }

    @GetMapping("/{name}")
    public String findUserPath(@PathVariable("name") String name, HttpServletRequest request, Model model){
        //Session Checking
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/login";
        }
        List<User> users = userRepository.findLike(name);
        model.addAttribute("users", users);
        return "/user/found";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam("name") String name){
        return "redirect:/user/"+name;
    }
}
