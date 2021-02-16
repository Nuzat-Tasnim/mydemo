package com.example.mydemo.BL.builder;

import com.example.mydemo.DAL.model.User;
import org.springframework.stereotype.Service;

@Service
public class userBuilder {

    private String name;
    private String email;
    private String password;
    private boolean admin;

    public User getuser(User user,String name, String email, String password){
        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
    public User createuser(){
        return new User(admin,name,email,password);

    }
    public userBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public userBuilder setEmail(String email) {
        this.email = email;
        return this;
    }
    public userBuilder setPassword(String password) {
        this.password = password;
        return this;
    }
    public userBuilder setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

}
