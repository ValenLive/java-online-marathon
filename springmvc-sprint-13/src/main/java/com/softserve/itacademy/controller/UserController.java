package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.RoleService;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "create-user";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "create-user";
        }
        user.setRole(roleService.readById(2L));
        userService.create(user);
        return "/todos-user";
    }

    @GetMapping("/{id}/read")
    public String read(@PathVariable(value="id") long id, Model model) {
        model.addAttribute(userService.readById(id).getMyTodos());
        return "todos-user";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.readById(Long.parseLong(id)));
        model.addAttribute("roles", roleService.getAll());
        return "update-user";
    }

    @PostMapping("/{id}/update")
    public String update(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return  "update-user";
        }
        if (user.getPassword().isBlank()) user.setPassword(userService.readById(user.getId()).getPassword());
        userService.update(user);
        return "redirect:/home";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value="id") long id) {
        userService.delete(id);
        return "redirect:/home";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users-list";
    }
}
