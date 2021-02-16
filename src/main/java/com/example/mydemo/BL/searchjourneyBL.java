package com.example.mydemo.BL;

import com.example.mydemo.DAL.model.Journey;
import com.example.mydemo.DAL.journeyDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class searchjourneyBL {

    @Autowired
    journeyDAL journeyRepo;
    @Autowired
    bookmarkBL bookmarkBl;
    @Autowired
    showPage showpage;

    public Map getmodel(){
        return showpage.getmodelhome();
    }

    public Map getmodel(int elementid){

        Journey journey = journeyRepo.findJourneyByJourneyId(elementid);
        boolean bookmark = bookmarkBl.getbookmarkjourney(elementid);
        return showpage.getModel("journey",journey,bookmark);
    }
    public Map getmodel(String msg){
        return showpage.getmodelerror(msg);
    }
    public Map getmodel(String source, String destination){
        List<Journey> journeys = journeyRepo.getAllBySource_PlaceNameAndDestination_PlaceName(source,destination);
        if(journeys.size()==0) return null;
        return showpage.getModeljourneys(journeys);
    }

}
