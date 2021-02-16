package com.example.mydemo.BL;

import com.example.mydemo.BL.builder.journeybuilder;
import com.example.mydemo.DAL.model.Journey;
import com.example.mydemo.DAL.model.Place;
import com.example.mydemo.DAL.journeyDAL;
import com.example.mydemo.DAL.placeDAL;
import com.example.mydemo.DAL.transportDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class editjourneyBL {
    @Autowired
    bookmarkBL bookmarkBl;
    @Autowired
    placeDAL placeRepo;
    @Autowired
    journeyDAL journeyRepo;
    @Autowired
    transportDAL transportRepo;
    @Autowired
    showPage showpage;

    public Map getmodelform(int elementid){
        return showpage.getmodeledit("journey",journeyRepo.findJourneyByJourneyId(elementid));
    }
    public Map getmodelerror(String msg){
        return showpage.getmodelerror(msg);
    }
    public Map getmodel(int elementid,int sourceid,int destinationid,String description){
        Journey journey = journeyRepo.findJourneyByJourneyId(elementid);
        Journey journeyNew = (Journey) journey.clone();

        Place source = placeRepo.findPlaceByPlaceId(sourceid);
        Place destination = placeRepo.findPlaceByPlaceId(destinationid);
        if(source==null||destination==null) return null;

        journeyNew = new journeybuilder().build(journey,source,destination,description);
        journeyRepo.save(journeyNew);

        boolean bookmark = bookmarkBl.getbookmarkjourney(elementid);
        return showpage.getModel("journey",journeyNew,bookmark);
    }
}
