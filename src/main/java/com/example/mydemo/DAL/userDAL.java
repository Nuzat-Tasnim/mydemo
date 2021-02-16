package com.example.mydemo.DAL;

import com.example.mydemo.DAL.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userDAL extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
    public User findByUserId(int id);
}
