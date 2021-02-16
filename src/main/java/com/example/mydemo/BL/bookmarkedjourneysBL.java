package com.example.mydemo.BL;

import com.example.mydemo.DAL.interfaces.Iterator;
import com.example.mydemo.DAL.model.Journey;
import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.DAL.journeyDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class bookmarkedjourneysBL {
    @Autowired
    journeyDAL journeyRepo;
    @Autowired
    showPage showpage;

    public User getuser(){
        return (User) Session.getcurrentSession().getSession().getAttribute("user");
    }
    public Map getmodel(){
        Iterator iterator = getuser().getBookmarkedJourneys().getIterator();
        List<Journey> list = new ArrayList<>();
        while(iterator.hasNext()){
            list.add(journeyRepo.findJourneyByJourneyId(iterator.next().getId()));
        }
        if(list.size()<1) return null;
        return showpage.getModeljourneys(list);
    }
    public Map getmodel(String msg){
        return showpage.getmodelerror(msg);
    }
}
