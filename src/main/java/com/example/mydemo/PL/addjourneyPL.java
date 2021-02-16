package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.addjourneyBL;
import com.example.mydemo.BL.loginBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class addjourneyPL {
    @Autowired
    loginBL loginBl;
    @Autowired
    addjourneyBL addjourneyBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }
    public boolean admincheck(){
        User user = (User) Session.getcurrentSession().getSession().getAttribute("user");
        return user.isAdmin();
    }

    @RequestMapping("addJourneyForm")
    public ModelAndView check(){
        if(!isloggedin()) return new ModelAndView("index.jsp");

        if(!admincheck()) return new ModelAndView("error.jsp","model",addjourneyBl.getmodelerror("Only admin can update the data."));

        return new ModelAndView("addjourney.jsp","model",addjourneyBl.getmodelhome());
    }

    @RequestMapping("addJourney")
    public ModelAndView addjourney(
            @RequestParam("sourceid") int sourceid,
            @RequestParam("destinationid") int destinationid,
            @RequestParam(value="description", required = false) String description)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if(!admincheck()) return new ModelAndView("error.jsp","model",addjourneyBl.getmodelerror("Only admin can update the data."));

        Map<String, Object> model = addjourneyBl.getmodel(sourceid,destinationid,description);
        if(model==null) return new ModelAndView("error.jsp",addjourneyBl.getmodelerror("Invalid Data."));
        return new ModelAndView("journeypage.jsp","model",model);
    }

    @RequestMapping("addJourneyInfo")
    public ModelAndView addplaceinfo(@RequestParam("elementid") int elementid){

        if(!isloggedin()) return new ModelAndView("index.jsp");
        if(!admincheck()) return new ModelAndView("error.jsp","model",addjourneyBl.getmodelerror("Only admin can update the data."));

        return new ModelAndView("addjourneyinfo.jsp","model",addjourneyBl.getmodeledit(elementid));
    }

    @RequestMapping("addtransportjourney")
    public ModelAndView addtransport(
            @RequestParam(value = "elementid") int elementid,
            @RequestParam("transportname") String name,
            @RequestParam("rent") String rent)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if(!admincheck()) return new ModelAndView("error.jsp","model",addjourneyBl.getmodelerror("Only admin can update the data."));

        return new ModelAndView("addjourneyinfo.jsp","model",addjourneyBl.savetransport(elementid,name,rent));
    }

    @RequestMapping("deleteTransportJourney")
    public ModelAndView deleteTransport(@RequestParam("elementid") int elementid,
                                        @RequestParam("transportid") int transportid)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if(!admincheck()) return new ModelAndView("error.jsp","model",addjourneyBl.getmodelerror("Only admin can update the data."));

        return new ModelAndView("addjourneyinfo.jsp","model",addjourneyBl.deleteTransport(elementid,transportid));
    }

    @RequestMapping("/backtojourney")
    public ModelAndView goback(@RequestParam("elementid") int elementid){
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("journeypage.jsp","model",addjourneyBl.getsavedmodel(elementid));
    }
}
