package com.example.marketplace.marketplace.Repos;

import com.example.marketplace.marketplace.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    //User findByUsername(String username);
    //User findByUsername(String username);

}
