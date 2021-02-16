package com.example.mydemo.BL.reusablecode;

import com.example.mydemo.DAL.interfaces.Result;
import com.example.mydemo.DAL.model.Journey;
import com.example.mydemo.DAL.model.Place;
import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class showPage {

    public Map<String, Object> model = new HashMap<String, Object>();

    public User getuser(){
        return (User) Session.getcurrentSession().getSession().getAttribute("user");
    }


    public Map getModel(String element,Result result, boolean bookmark){
        model.put(element, result);
        model.put("bookmark",bookmark);
        model.put("user",getuser());
        return model;
    }
    public Map getmodeledit(String element, Result result){
        model.put(element,result);
        model.put("user",getuser());
        return model;
    }
    public Map getModelplaces( List<Place> list){
        model.put("places",list);
        model.put("user",getuser());
        return model;
    }
    public Map getModeljourneys(List<Journey> list){
        model.put("journeys",list);
        model.put("user",getuser());
        return model;
    }
    public Map getmodelhome(){
        model.put("user",getuser());
        return model;
    }
    public Map getmodelerror(String msg){
        model.put("user",getuser());
        model.put("msg",msg);
        return model;
    }

}
