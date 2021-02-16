package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.loginBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginPL {

    @Autowired
    loginBL loginbl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }

    @RequestMapping("/loginform")
    public ModelAndView display(){
        if(isloggedin()) return new ModelAndView("home.jsp","model",loginbl.getmodelhome());

        return new ModelAndView("login.jsp");
    }

    @RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session)
    {
        User user = loginbl.loggingin(email,password,session);
        if(user!=null) {
            return new ModelAndView("home.jsp","model",loginbl.getmodelhome());
        }
        else{
            return new ModelAndView("authentication.jsp");
        }
    }

    @RequestMapping(value = "logout", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView logout() {
        loginbl.logout();
        return new ModelAndView("index.jsp");
    }
}