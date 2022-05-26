package proiect.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import proiect.entity.User;
import proiect.service.UserService;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLogInForm(){ return "/home/login"; }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("errorMessage","Invalid user or password");
        return "/home/login_failed";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "/home/access_denied";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "/home/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/home/signup";
        }

        User savedUser = userService.save(user);

        return "redirect:/";
    }
}
