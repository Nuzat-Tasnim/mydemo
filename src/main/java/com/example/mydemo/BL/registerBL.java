package com.example.mydemo.BL;

import com.example.mydemo.BL.builder.userBuilder;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.DAL.userDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class registerBL {

    @Autowired
    userDAL userRepo;
    @Autowired
    showPage showpage;
    @Autowired
    loginBL loginBl;

    public User register(String name, String email, String pass1, String pass2, HttpSession session){
        if(verify(name,email,pass1,pass2)){
            User user = new userBuilder().setAdmin(false).setEmail(email).setName(name).setPassword(pass1).createuser();
            userRepo.save(user);
            return loginBl.loggingin(email,pass1,session);
        }
        return null;
    }

    public boolean verify(String name, String email, String pass1, String pass2){
        if(verifyName(name) && verifyEmail(email) && verifyPass(pass1,pass2)) return true;
        return false;
    }
    public boolean verifyName(String name){
        return (name.length()>2);
    }

    public boolean verifyPass(String pass1,String pass2){
        if(pass1.equals(pass2) && pass1.length()>2)
            return true;
        return false;
    }

    public boolean verifyEmail(String email){
        return (userRepo.findByEmail(email)==null);
    }

    public Map getmodelhome(){
        return showpage.getmodelhome();
    }


}
