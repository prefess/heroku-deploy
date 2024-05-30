package com.example.SpringBootDemoProject.controller;

import com.example.SpringBootDemoProject.dto.UserDTO;
import com.example.SpringBootDemoProject.exception.UserException;
import com.example.SpringBootDemoProject.model.User;
import com.example.SpringBootDemoProject.request.LoginRequest;
import com.example.SpringBootDemoProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping
    public String showHomePage(Model model, HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null) {
            model.addAttribute("user", userDTO);
        }
        return "index";
    }

    @GetMapping("login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginrequest", new LoginRequest("", ""));
        return "login";
    }

    @PostMapping("login")
    public String handleLogin(@Valid @ModelAttribute("loginrequest") LoginRequest loginRequest,
                              BindingResult result, HttpSession httpSession) {
        if (result.hasErrors()) {
            return "login";
        }
        User user;

        try {
            user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            httpSession.setAttribute("user", new UserDTO(user.getId(), user.getFullname(), user.getEmail()));
            return "redirect:/";
        } catch (UserException ex) {
            switch (ex.getMessage()) {
                case "User is not found!":
                    result.addError(new FieldError("loginrequest", "email", "Email does not exist"));
                    break;
                case "User is not activated":
                    result.addError(new FieldError("loginrequest", "email", "User is not activated"));
                    break;
                case "Password is incorrect":
                    result.addError(new FieldError("loginrequest", "password", "Password is incorrect"));
                    break;
            }
            System.out.println(ex.getMessage());
            return "login";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("welcome")
    public String showWelcomePage() {
        return "welcome";
    }

    @GetMapping("admin")
    public String showAdminPage(HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null) {
            return "admin";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("foo")
    public String foo() {
        throw new UserException("Some error");
    }

}
