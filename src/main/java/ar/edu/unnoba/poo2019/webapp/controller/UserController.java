/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.controller;

import ar.edu.unnoba.poo2019.webapp.model.User;
import ar.edu.unnoba.poo2019.webapp.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jpgm
 */
@Controller
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String index(Model model){
        List<User> users = userService.users();
        model.addAttribute("users", users);
        return "users/index";
    }
    
    @GetMapping("/new")
    public String userNew(Model model){
        model.addAttribute("user", new User());
        return "users/new";
    }
    
    @PostMapping
    public String create(@ModelAttribute User user){
        userService.create(user);
        return "redirect:/users";
    }
    
     @GetMapping("/{id}/delete")
    public String delete(@PathVariable ("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model, Authentication authentication){
        User sessionUser = (User)authentication.getPrincipal();
        sessionUser.getEmail();
        User user = userService.find(id);
        model.addAttribute("user", user);
        return "users/edit";
    }
    
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,@ModelAttribute User user){
        userService.update(id,user);
        return "redirect:/users";
    }
    
}
