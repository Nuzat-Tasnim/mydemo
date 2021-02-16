package com.example.mydemo.BL;

import com.example.mydemo.DAL.interfaces.Iterator;
import com.example.mydemo.DAL.model.Place;
import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.DAL.placeDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class bookmarkedplacesBL {
    @Autowired
    placeDAL placeRepo;
    @Autowired
    showPage showpage;

    public User getuser(){
        return (User) Session.getcurrentSession().getSession().getAttribute("user");
    }

    public Map getmodel(){
        Iterator iterator = getuser().getBookmarkedPlaces().getIterator();
        List<Place> placeList = new ArrayList<Place>();
        while (iterator.hasNext()){
            placeList.add(placeRepo.findPlaceByPlaceId(iterator.next().getId()));
        }
        if(placeList.isEmpty()) return null;
        return showpage.getModelplaces(placeList);
    }
    public Map getmodel(String msg){
        return showpage.getmodelerror(msg);
    }
}
