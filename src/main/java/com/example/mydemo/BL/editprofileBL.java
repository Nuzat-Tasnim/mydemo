package com.example.mydemo.BL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.builder.userBuilder;
import com.example.mydemo.DAL.userDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class editprofileBL {

    @Autowired
    userDAL userRepo;
    @Autowired
    registerBL registerBl;
    @Autowired
    showPage showpage;

    public User getuser(){
        return (User) Session.getcurrentSession().getSession().getAttribute("user");
    }
    public void sessionactivity(User user){
        Session.getcurrentSession().getSession().removeAttribute("user");
        Session.getcurrentSession().getSession().setAttribute("user",user);
    }

    public User edit(String name, String email, String pass){
        User user = getuser();
        if(verify(user,name,email,pass)){
            user = userRepo.save(new userBuilder().getuser(user,name, email, pass));
            sessionactivity(user);
            return user;
        }
        else return null;
    }

    public boolean verify(User user, String name, String email, String pass){
        if(name.length()>0 && registerBl.verifyPass(pass,pass)){
            if(registerBl.verifyEmail(email)){
                return true;
            }
            else return (user.getEmail().equals(email));
        }
        return false;
    }

    public Map getmodelhome(){
        return showpage.getmodelhome();
    }

    public Map getmodelerror(String msg){
        return showpage.getmodelerror(msg);
    }
}
