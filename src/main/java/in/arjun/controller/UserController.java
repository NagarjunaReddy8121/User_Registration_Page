package in.arjun.controller;

import in.arjun.entity.User;
import in.arjun.request.UserRequest;
import in.arjun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model){
      UserRequest userRequest=new UserRequest();
        model.addAttribute("user",userRequest);
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") UserRequest userRequest, Model model){
        Optional<User> byEmailAndPassword = userService.getByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());
        if (byEmailAndPassword.isEmpty()){
            model.addAttribute("emsg","Invalid Credentials please register..");
            return "index";
        }
        return "dashboard";

    }

    @GetMapping("/register")
    public String registerPage(UserRequest userRequest,Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "register";
    }
    @PostMapping("/register")
    public String handleLoginBtn(@ModelAttribute("user") UserRequest userRequest, Model model){
        User user=User.builder().name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phoneNo(userRequest.getPhoneNo())
                .build();
        boolean isSaved = userService.createUser(user);
        if(isSaved) {
            model.addAttribute("smsg", "user added please login");
        }
        else {
                model.addAttribute("emsg","user not added this email ID already exists please login");
        }
        return "register";
    }
}
