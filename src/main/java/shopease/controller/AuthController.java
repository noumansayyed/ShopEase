package shopease.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shopease.dto.RegistrationDto;
import shopease.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ---------- LOGIN ----------

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    // ---------- REGISTER ----------

    @GetMapping("/register")
    public String registerForm(Model model) {
        if (!model.containsAttribute("registrationDto")) {
            model.addAttribute("registrationDto", new RegistrationDto());
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerSubmit(
            @Valid @ModelAttribute("registrationDto") RegistrationDto dto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        // 1. Annotation-level validation (@NotBlank, @Email, @Size)
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        // 2. Business-level validation (mismatch, duplicate email)
        try {
            userService.registerNewUser(dto);
        } catch (IllegalArgumentException ex) {
            bindingResult.reject("registrationError", ex.getMessage());
            return "auth/register";
        }

        redirectAttributes.addFlashAttribute("successMessage",
                "Registration successful. Please log in.");
        return "redirect:/login";
    }
}