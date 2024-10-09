package com.authentication.controllers;

import com.authentication.entities.UserAccount;
import com.authentication.services.UserAccountService;
import com.authentication.utility.APIResponse;
import com.authentication.utility.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class LoginController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/login")
    APIResponse login(@RequestBody LoginUser loginUser){
       return userAccountService.loginUser(loginUser);
    }
    @PostMapping("/register")
    APIResponse saveUserAccount(@RequestBody UserAccount userAccount)
    {
        return userAccountService.saveUserAccount(userAccount);
    }
}
