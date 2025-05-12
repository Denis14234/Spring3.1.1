package spring.Module31.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.Module31.model.User;
import spring.Module31.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String redirectToUsers() {
        return "redirect:/users";
    }

    @GetMapping(value = "/users")
    public String printUsers(@RequestParam(defaultValue = "5") int count, ModelMap model) {
        model.addAttribute("users", userService.getUserList(count));
        return "users";
    }

    @PostMapping(value = "/users/add")
    public String addUser (@ModelAttribute User user) {
        userService.addUser (user);
        return "redirect:/users";
    }

    @PostMapping(value = "/users/delete")
    public String deleteUser (@RequestParam Long id) {
        userService.deleteUser (id);
        return "redirect:/users";
    }

    @PostMapping(value = "/users/update")
    public String updateUser (@ModelAttribute User user) {
        User existingUser  = userService.getUserById(user.getId());
        if (existingUser  != null) {
            existingUser .setFirstName(user.getFirstName());
            existingUser .setLastName(user.getLastName());
            existingUser .setEmail(user.getEmail());
            userService.updateUser (existingUser );
        }
        return "redirect:/users";
    }
}
