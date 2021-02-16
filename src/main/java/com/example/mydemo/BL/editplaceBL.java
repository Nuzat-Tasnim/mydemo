package com.example.mydemo.BL;

import com.example.mydemo.BL.builder.placebuilder;
import com.example.mydemo.DAL.model.Place;
import com.example.mydemo.DAL.placeDAL;
import com.example.mydemo.DAL.transportDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class editplaceBL {
    @Autowired
    bookmarkBL bookmarkBl;
    @Autowired
    placeDAL placeRepo;
    @Autowired
    transportDAL transportRepo;
    @Autowired
    showPage showpage;

    public Map getmodel(int elementid){
        Place place = placeRepo.findPlaceByPlaceId(elementid);
        return showpage.getmodeledit("place",place);
    }
    public Map getmodel(int elementid, String placeName, String description){
        Place place = placeRepo.findPlaceByPlaceId(elementid);
        Place placenew = (Place) place.clone();
        placenew = new placebuilder().build(placenew,placeName,description);
        boolean bookmark = bookmarkBl.getbookmarkplace(elementid);
        return showpage.getModel("place",placeRepo.save(placenew),bookmark);
    }
    public Map getmodelerror(String msg){
        return showpage.getmodelerror(msg);
    }
}
