package crud.controller;

import crud.entity.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showAllUsers(ModelMap model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }


    @GetMapping("/user-add")
    public String addUserPage(@ModelAttribute User user) {
        return "user-add";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute User user) {
        System.out.println("Saving user: " + user); // Отладочное сообщение
        userService.save(user);
        return "redirect:/user/users";
    }

    @GetMapping("/update-page/{id}")
    public String updateUserPage(ModelMap model, @PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "user-update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/user/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/user/users";
    }
}