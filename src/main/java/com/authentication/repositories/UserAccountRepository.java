package com.authentication.repositories;

import com.authentication.config.CustomUserDetailsService;
import com.authentication.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String userName);

}
