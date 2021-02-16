package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.editjourneyBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class editJourneyPL {
    @Autowired
    editjourneyBL editjourneyBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }
    public boolean admincheck(){
        User user = (User) Session.getcurrentSession().getSession().getAttribute("user");
        return user.isAdmin();
    }

    @RequestMapping("editJourney")
    public ModelAndView editform(@RequestParam("elementid") int elementid){
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if(!admincheck()) return new ModelAndView("error.jsp","model",editjourneyBl.getmodelerror("Only admin can update the data."));

        return new ModelAndView("editjourney.jsp","model",editjourneyBl.getmodelform(elementid));
    }

    @RequestMapping("saveEditJourney")
    public ModelAndView edit(@RequestParam("elementid") int elementid,
                             @RequestParam("sourceid") int sourceid,
                             @RequestParam("destinationid") int destinationid,
                             @RequestParam(value="description", required = false) String description)
    {

        if(!isloggedin()) return new ModelAndView("index.jsp");
        if(!admincheck()) return new ModelAndView("error.jsp","model",editjourneyBl.getmodelerror("Only admin can update the data."));

        Map<String, Object> model = editjourneyBl.getmodel(elementid,sourceid,destinationid,description);
        if(model==null) return new ModelAndView("error.jsp",editjourneyBl.getmodelerror("Invalid Data."));
        return new ModelAndView("journeypage.jsp","model",model);
    }
}
