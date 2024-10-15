package com.authentication.controllers;

import com.authentication.entities.UserAccount;
import com.authentication.services.UserAccountService;
import com.authentication.utility.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    public UserAccountService userAccountService;

    @GetMapping("/{id}")
    APIResponse getUserAccount(@PathVariable("id") Long id)
    {
        return userAccountService.getUserAccount(id);

    }
    @PutMapping
    APIResponse updateUserAccount(@RequestBody UserAccount userAccount)
    {
       return userAccountService.updateUserAccount(userAccount);
    }
    @DeleteMapping("/{id}")
    APIResponse deleteUserAccount(@PathVariable Long id)
    {
        return userAccountService.deleteUserAccount(id);
    }
    @GetMapping
    APIResponse getAllUsers()
    {
        return userAccountService.getAllUserAccounts();
    }

    @GetMapping("/hh")
    String listToHashmap()
    {
        List<String> stringList = Arrays.asList("A", "B", "C", "D", "C", "B", "A", "A");
        HashMap<String, Integer> occurenceValue = new HashMap<>();
        for (String string : stringList)
        {
            if (occurenceValue.containsKey(string))
            {
                occurenceValue.put(string, occurenceValue.get(string) + 1);
            }
            else {
                occurenceValue.put(string, 1);
            }
        }
        return "Hello";
    }
}
