package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.reviewBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class reviewPL {
    @Autowired
    reviewBL reviewBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }

    @RequestMapping(value = "/addreviewplace", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addreviewplace(@RequestParam(value = "elementid") int elementid,
                                  @RequestParam("review") String text){
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("placepage.jsp","model", reviewBl.getmodelplace(elementid, text));
    }
    @RequestMapping(value = "/addreviewjourney", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addreviewjourney(@RequestParam(value = "elementid") int elementid,
                                  @RequestParam("review") String text){
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("journeypage.jsp","model", reviewBl.getmodeljourney(elementid, text));
    }
}
