package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.reusablecode.showPage;
import com.example.mydemo.BL.registerBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class registerPL {

    @Autowired
    registerBL regBL;
    @Autowired
    showPage showpage;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }

    @RequestMapping("/register")
    public ModelAndView display(){
        if(isloggedin()) return new ModelAndView("home.jsp","model",regBL.getmodelhome());
        return new ModelAndView("register.jsp");
    }

    @RequestMapping("/addUser")
    public ModelAndView addUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confPass") String confPass,
            HttpSession session)
    {
        if(isloggedin()) return new ModelAndView("home.jsp","model",regBL.getmodelhome());

        User user = regBL.register(name,email,password,confPass,session);

        if(user!=null){
            return new ModelAndView("home.jsp","model",regBL.getmodelhome());
        }
        else{
            return new ModelAndView("authentication.jsp");
        }
    }
}

