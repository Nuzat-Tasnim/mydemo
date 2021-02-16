package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.editprofileBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class editprofilePL {
    @Autowired
    editprofileBL editprofileBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }

    @RequestMapping("editProfilePage")
    public ModelAndView editform(HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println(user.getEmail());
        if(!isloggedin()) return new ModelAndView("index.jsp");

        return new ModelAndView("editProfile.jsp","model",editprofileBl.getmodelhome());
    }

    @RequestMapping("editProfile")
    public ModelAndView edit(@RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        User user = editprofileBl.edit(name,email,password);
        if(user!=null)
            return new ModelAndView("home.jsp","model",editprofileBl.getmodelhome());
        else{
            return new ModelAndView("error.jsp","model",editprofileBl.getmodelerror("Invalid Credentials"));
        }
    }
}
