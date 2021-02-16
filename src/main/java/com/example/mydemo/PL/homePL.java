package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.homeBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class homePL{
    @Autowired
    homeBL homeBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index.jsp");
    }

    @RequestMapping("/home")
    public ModelAndView home(){
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("home.jsp", "model", homeBl.getmodelhome());
    }
}
