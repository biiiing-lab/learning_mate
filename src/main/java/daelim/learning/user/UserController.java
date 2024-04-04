package daelim.learning.user;

import daelim.learning.user.dto.JoinRequest;
import daelim.learning.user.dto.LoginRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginRequest loginRequest, HttpSession session, RedirectAttributes redirectAttributes) {
        userService.login(loginRequest, session, redirectAttributes);
        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinForm(Model model, JoinRequest joinRequest) {
        model.addAttribute("joinRequest", joinRequest);
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("joinRequest") JoinRequest request, BindingResult bindingResult) {
        String url = userService.join(request, bindingResult);
        return url;
    }
}