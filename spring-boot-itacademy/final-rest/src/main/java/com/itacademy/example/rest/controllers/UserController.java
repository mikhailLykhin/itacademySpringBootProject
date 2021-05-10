package com.itacademy.example.rest.controllers;

import com.itacademy.example.dto.PetDtoWrapper;
import com.itacademy.example.dto.UserDto;
import com.itacademy.example.dto.UserPetIdsDto;
import com.itacademy.example.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping()
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());

        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("users", this.userService.getUsers());

        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users/byname")
    public String findUser(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("user", this.userService.findByName(name));
        model.addAttribute("users", userService.getUsers());
        return "userPage";
    }

    @GetMapping("/admin/users/{id}")
    public String findUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.findUser(id));
        return "userPage";
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        model.addAttribute("title", "Lets see all users: ");
        model.addAttribute("users", userService.getUsers());
        return "usersPage";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") UserDto user) {
        return "newUser";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") @Valid UserDto user,
                             BindingResult bindingResult,
                             Model model,
                             @RequestParam(value = "file", required = false) MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "newUser";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "newUser";
        }
        if (!userService.createUser(user, file)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "newUser";
        }

        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser(Principal principal, Model model) {
        model.addAttribute("user", this.userService.findByName(principal.getName()));

        return "editUser";
    }

    @PatchMapping("/edit")
    public String updateUser(Principal principal,
                             @ModelAttribute("user") UserDto user,
                             Model model,@RequestParam(value = "file", required = false) MultipartFile file) {
       if (!this.userService.updateUser(principal, user, file)) {
           model.addAttribute("confirmPasswordError", "Существующий пароль введен неверно");
           return "editUser";
       }
        return "redirect:/";
    }

    @DeleteMapping("/admin/users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        this.userService.deleteUser(id);
        return "redirect:/admin/users";
    }

//    @PatchMapping("/{id}")
//    public void assignPetToUser(@PathVariable int id, @RequestBody PetDto pet) {
//        this.userService.assignPetToUser(id, pet);
//    }

    @PatchMapping()
    public void assignPetToUser(@RequestBody UserPetIdsDto ids) {
        this.userService.assignPetToUser(ids);
    }

    @PatchMapping("/edit/{id}")
    public void assignAllPetsToUser(@PathVariable int id, @RequestBody PetDtoWrapper pets) {
        this.userService.assignAllPetsToUser(id, pets);
    }
}
