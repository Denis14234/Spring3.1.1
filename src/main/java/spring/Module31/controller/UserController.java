package spring.Module31.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
        public String addUser (@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email) {
            User user = new User(firstName, lastName, email);
            userService.addUser (user);
            return "redirect:/users";
        }

        @PostMapping(value = "/users/delete")
        public String deleteUser (@RequestParam Long id) {
            userService.deleteUser (id);
            return "redirect:/users";
        }

        @PostMapping(value = "/users/update")
        public String updateUser (@RequestParam Long id,
                                  @RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam String email) {
            User user = userService.getUserById(id);
            if (user != null) {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                userService.updateUser (user);
            }
            return "redirect:/users";
        }
    }

