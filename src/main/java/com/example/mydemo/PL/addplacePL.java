package com.example.mydemo.PL;

import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.BL.addplaceBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class addplacePL {
    @Autowired
    addplaceBL addplaceBl;

    public boolean isloggedin(){
        return Session.getcurrentSession().getSession()!=null;
    }
    public boolean admincheck(){
        User user = (User) Session.getcurrentSession().getSession().getAttribute("user");
        return user.isAdmin();
    }

    @RequestMapping("addPlaceForm")
    public ModelAndView check(){
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if (!admincheck()) return new ModelAndView("error.jsp","model",addplaceBl.getmodel("Only admin can update the data."));

        return new ModelAndView("addplace.jsp","model",addplaceBl.getmodel());
    }

    @RequestMapping("addPlace")
    public ModelAndView addplace(
            @RequestParam("placeName") String placeName,
            @RequestParam("description") String description,
            HttpSession session)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if (!admincheck()) return new ModelAndView("error.jsp","model",addplaceBl.getmodel("Only admin can update the data."));

        return new ModelAndView("placepage.jsp","model",addplaceBl.getmodel(placeName,description));
    }

    @RequestMapping("addPlaceInfo")
    public ModelAndView addplaceinfo(@RequestParam("elementid") int elementid){
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("addplaceinfo.jsp","model",addplaceBl.getmodeledit(elementid));
    }

    @RequestMapping("addhospitalContact")
    public ModelAndView addhospitalContact(
            @RequestParam(value = "elementid") int elementid,
            @RequestParam("hospName") String name,
            @RequestParam("hospAddress") String address,
            @RequestParam("hospContact") String contact)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if (!admincheck()) return new ModelAndView("error.jsp","model",addplaceBl.getmodel("Only admin can update the data."));

        return new ModelAndView("addplaceinfo.jsp","model",addplaceBl.savehospcontact(elementid,name,address,contact));
    }

    @RequestMapping("deleteHospital")
    public ModelAndView deleteHospital(@RequestParam("elementid") int elementid,
                                       @RequestParam("contactid") int contactid)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if (!admincheck()) return new ModelAndView("error.jsp","model",addplaceBl.getmodel("Only admin can update the data."));

        return new ModelAndView("addplaceinfo.jsp","model",addplaceBl.deleteHospital(elementid,contactid));
    }

    @RequestMapping("addpolicecontact")
    public ModelAndView addpolicecontact(
            @RequestParam("elementid") int elementid,
            @RequestParam("stationName") String name,
            @RequestParam("stationAddress") String address,
            @RequestParam("stationContact") String contact)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if (!admincheck()) return new ModelAndView("error.jsp","model",addplaceBl.getmodel("Only admin can update the data."));

        return new ModelAndView("addplaceinfo.jsp","model",addplaceBl.savepolicecontact(elementid,name,address,contact));
    }

    @RequestMapping("deletePolice")
    public ModelAndView deletePolice(@RequestParam("elementid") int elementid,
                                     @RequestParam("contactid") int contactid)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if (!admincheck()) return new ModelAndView("error.jsp","model",addplaceBl.getmodel("Only admin can update the data."));

        return new ModelAndView("addplaceinfo.jsp","model",addplaceBl.deletePolice(elementid,contactid));
    }

    @RequestMapping("addtransportplace")
    public ModelAndView addtransport(
            @RequestParam("elementid") int elementid,
            @RequestParam("transportname") String name,
            @RequestParam(value="availability", required = false) String availability)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if (!admincheck()) return new ModelAndView("error.jsp","model",addplaceBl.getmodel("Only admin can update the data."));

        return new ModelAndView("addplaceinfo.jsp","model",addplaceBl.savetransport(elementid,name,availability));
    }

    @RequestMapping("deleteTransportPlace")
    public ModelAndView deleteTransport(@RequestParam("elementid") int elementid,
                                     @RequestParam("transportid") int transportid)
    {
        if(!isloggedin()) return new ModelAndView("index.jsp");
        if (!admincheck()) return new ModelAndView("error.jsp","model",addplaceBl.getmodel("Only admin can update the data."));

        return new ModelAndView("addplaceinfo.jsp","model",addplaceBl.deleteTransport(elementid,transportid));
    }

    @RequestMapping("/backtoplace")
    public ModelAndView goback(@RequestParam("elementid") int elementid){
        if(!isloggedin()) return new ModelAndView("index.jsp");
        return new ModelAndView("placepage.jsp","model",addplaceBl.getsavedmodel(elementid));
    }

}
