package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.bookmarkBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class bookmarkPL {
    @Autowired
    bookmarkBL bookmarkBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }

    @RequestMapping("bookmarkplace")
    public ModelAndView bookmarkplace(@RequestParam("elementid") int elementid) {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("placepage.jsp",  "model", bookmarkBl.bookmarkplace(elementid));
    }

    @RequestMapping("unbookmarkplace")
    public ModelAndView unbookmarkplace(@RequestParam("elementid") int elementid) {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("placepage.jsp",  "model", bookmarkBl.unbookmarkplace(elementid));
    }

    @RequestMapping("bookmarkjourney")
    public ModelAndView bookmarkjourney(@RequestParam("elementid") int elementid) {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("journeypage.jsp",  "model", bookmarkBl.bookmarkjourney(elementid));
    }
    @RequestMapping("unbookmarkjourney")
    public ModelAndView unbookmarkjourney(@RequestParam("elementid") int elementid) {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("journeypage.jsp",  "model", bookmarkBl.unbookmarkjourney( elementid));
    }
}
