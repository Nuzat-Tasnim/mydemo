package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.searchplaceBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class searchplacePL {

    @Autowired
    searchplaceBL searchplaceBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }

    @RequestMapping(value = "/searchplaceform", method = RequestMethod.GET)
    public ModelAndView searchPlace() {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("searchPlace.jsp",  "model", searchplaceBl.getmodel());
    }

    @RequestMapping(value = "searchPlaceList", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView searchPlaceList(@RequestParam("place") String name){
        if(!isloggedin()) return new ModelAndView("index.jsp");

        Map<String, Object> model = searchplaceBl.getmodel(name);
        if(model==null) return new ModelAndView("error.jsp", "model", searchplaceBl.getmodelerror("No such results"));
        return new ModelAndView("placeList.jsp", "model", model);

    }
    @RequestMapping(value = "/searchPlace", method = RequestMethod.GET)
    public ModelAndView searchPlace(@RequestParam("elementid") int elementid) {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("placepage.jsp",  "model", searchplaceBl.getmodel(elementid));
    }
}
