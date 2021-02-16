package com.example.mydemo.BL;

import com.example.mydemo.DAL.model.Place;
import com.example.mydemo.DAL.placeDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class searchplaceBL {

    @Autowired
    placeDAL placeRepo;
    @Autowired
    bookmarkBL bookmarkBl;
    @Autowired
    showPage showpage;

    public Map getmodel(){
        return showpage.getmodelhome();
    }

    public Map getmodel(int elementid){
        Place place = placeRepo.findPlaceByPlaceId(elementid);
        boolean bookmark = bookmarkBl.getbookmarkplace(elementid);
        return showpage.getModel("place",place,bookmark);
    }
    public Map getmodelerror(String msg){
        return showpage.getmodelerror(msg);
    }
    public Map getmodel(String name){
        List<Place> places = placeRepo.findAllByPlaceNameContaining(name);
        if(places.size()==0) return null;
        return showpage.getModelplaces(places);
    }
}
