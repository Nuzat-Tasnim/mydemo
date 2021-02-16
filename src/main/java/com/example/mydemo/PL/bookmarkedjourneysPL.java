package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.bookmarkedjourneysBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class bookmarkedjourneysPL {
    @Autowired
    bookmarkedjourneysBL bookmarkedjourneysBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }

    @RequestMapping("/bookmarkedJourneys")
    public ModelAndView bookmarklist(){
        if(!isloggedin()) return new ModelAndView("index.jsp");

        Map<String, Object> model = bookmarkedjourneysBl.getmodel();
        if(model==null) return new ModelAndView("error.jsp", "model", bookmarkedjourneysBl.getmodel("No Bookmarked Journeys"));
        return new ModelAndView("JourneyList.jsp", "model", model);
    }
}
