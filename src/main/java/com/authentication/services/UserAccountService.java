package com.authentication.services;

import com.authentication.config.JWTService;
import com.authentication.entities.UserAccount;
import com.authentication.repositories.UserAccountRepository;
import com.authentication.utility.APIResponse;
import com.authentication.utility.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private APIResponse response;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public APIResponse saveUserAccount(UserAccount userAccount) {
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        UserAccount savedUserAccount = userAccountRepository.save(userAccount);
        response.setResponse(savedUserAccount);
        response.setStatus(HttpStatus.OK);
        response.setMessage("User Saved Successfully!");
        return response;
    }
    public APIResponse updateUserAccount(UserAccount userAccount) {

        if (userAccount.getId() != null && userAccount.getId() != 0) {
            Optional<UserAccount> foundUser = userAccountRepository.findById(userAccount.getId());

            if (foundUser.isPresent()) {
                userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
                UserAccount savedUserAccount = userAccountRepository.save(userAccount);

                response.setResponse(savedUserAccount);
                response.setStatus(HttpStatus.OK);
                response.setMessage("User updated successfully!");
            } else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("User not found.");
                response.setResponse(null);
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Invalid user ID.");
            response.setResponse(null);
        }

        return response;
    }
    public APIResponse deleteUserAccount(Long id) {
        if (id != null && id != 0) {
            Optional<UserAccount> foundUser = userAccountRepository.findById(id);

            if (foundUser.isPresent()) {
                userAccountRepository.delete(foundUser.get());

                response.setStatus(HttpStatus.OK);
                response.setResponse(null);
                response.setMessage("User deleted successfully!");
            } else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setResponse(null);
                response.setMessage("User not found.");
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setResponse(null);
            response.setMessage("Invalid user ID.");
        }
        return response;
    }
    public APIResponse getUserAccount(Long id) {
        if(id != null && id != 0) {
            Optional<UserAccount> foundUser = userAccountRepository.findById(id);
            if(foundUser.isPresent()) {
                response.setResponse(foundUser.orElse(null));
                response.setStatus(HttpStatus.OK);
                response.setMessage("User found Successfully!");
            }
           else {
                response.setResponse(null);
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("User not found.");
            }
        }
        else{
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User not found.");
            response.setResponse(null);
        }

        return response;
    }

    public APIResponse getAllUserAccounts() {
        List<UserAccount> accounts = userAccountRepository.findAll();
        response.setResponse(accounts);
        response.setMessage("All user accounts fetched successfully!");
        response.setStatus(HttpStatus.OK);
        return response;
    }

    public APIResponse loginUser(LoginUser loginUser) {
         Authentication authUser = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
         Authentication authentication = authenticationManager.authenticate(authUser);
        if(authentication.isAuthenticated())
        {
            String token = jwtService.generateToken(loginUser.username);
            response.setResponse(token);
            response.setStatus(HttpStatus.OK);
            response.setMessage("User logged in successfully!");
        }
        else {
            response.setResponse(null);
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Invalid user User Credentials!");
        }

        return response;
    }
}
