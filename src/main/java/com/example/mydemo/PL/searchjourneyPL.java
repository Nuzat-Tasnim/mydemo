package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.searchjourneyBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class searchjourneyPL {

    @Autowired
    searchjourneyBL searchjourneyBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }

    @RequestMapping(value = "/searchjourneyform", method = RequestMethod.GET)
    public ModelAndView searchPlace() {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("searchJourney.jsp",  "model", searchjourneyBl.getmodel());
    }

    @RequestMapping(value = "searchJourneyList", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView searchJourneyList(@RequestParam("source") String source,
                                          @RequestParam("destination") String destination)
    {

        if(!isloggedin()) return new ModelAndView("index.jsp");
        Map<String, Object> model = searchjourneyBl.getmodel(source, destination);
        if(model==null) return new ModelAndView("error.jsp", "model", searchjourneyBl.getmodel("No such results"));
        return new ModelAndView("JourneyList.jsp", "model", model);
    }

    @RequestMapping(value = "/searchJourney", method = RequestMethod.GET)
    public ModelAndView searchJourney(@RequestParam(value = "elementid") int elementid) {

        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("journeypage.jsp", "model", searchjourneyBl.getmodel(elementid));
    }
}
