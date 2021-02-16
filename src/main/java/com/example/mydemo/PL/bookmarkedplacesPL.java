package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.bookmarkedplacesBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class bookmarkedplacesPL {
    @Autowired
    bookmarkedplacesBL bookmarkedplacesBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }

    @RequestMapping("/bookmarkedPlaces")
    public ModelAndView bookmarklist(){
        if(!isloggedin()) return new ModelAndView("index.jsp");

        Map<String, Object> model = bookmarkedplacesBl.getmodel();
        if(model==null) return new ModelAndView("error.jsp", "model", bookmarkedplacesBl.getmodel("No Bookmarked Places"));
        return new ModelAndView("placeList.jsp", "model", model);
    }
}
