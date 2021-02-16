package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.editplaceBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class editplacePL {
    @Autowired
    editplaceBL editplaceBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }
    public boolean admincheck(){
        User user = (User) Session.getcurrentSession().getSession().getAttribute("user");
        return user.isAdmin();
    }

    @RequestMapping("editPlace")
    public ModelAndView editform(@RequestParam("elementid") int elementid){
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if (!admincheck()) return new ModelAndView("error.jsp","model",editplaceBl.getmodelerror("Only admin can update the data."));

        return new ModelAndView("editplace.jsp","model",editplaceBl.getmodel(elementid));
    }
    @RequestMapping("saveEditPlace")
    public ModelAndView edit(@RequestParam("elementid") int elementid,
                             @RequestParam("placeName") String placeName,
                             @RequestParam("description") String description){

        if(!isloggedin()) return new ModelAndView("index.jsp");
        if (!admincheck()) return new ModelAndView("error.jsp","model",editplaceBl.getmodelerror("Only admin can update the data."));

        return new ModelAndView("placepage.jsp","model",editplaceBl.getmodel(elementid,placeName,description));
    }
}
