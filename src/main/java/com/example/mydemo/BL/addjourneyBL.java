package com.example.mydemo.BL;

import com.example.mydemo.DAL.model.Journey;
import com.example.mydemo.DAL.model.Place;
import com.example.mydemo.DAL.model.Transport;
import com.example.mydemo.DAL.journeyDAL;
import com.example.mydemo.DAL.placeDAL;
import com.example.mydemo.DAL.transportDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class addjourneyBL {
    @Autowired
    searchjourneyBL searchjourneyBl;
    @Autowired
    placeDAL placeRepo;
    @Autowired
    journeyDAL journeyRepo;
    @Autowired
    transportDAL transportRepo;
    @Autowired
    showPage showpage;

    public Map getsavedmodel(int elementid){
        return searchjourneyBl.getmodel(elementid);
    }
    public Map getmodeledit(int elementid){
        return showpage.getmodeledit("journey",journeyRepo.findJourneyByJourneyId(elementid));
    }

    public Map getmodel(int sourceid,int destinationid,String description){
        Journey journey = addjourney(sourceid,destinationid,description);
        if(journey==null) return null;
        return showpage.getModel("journey",journey,false);
    }

    public Journey addjourney(int sourceid,int destinationid,String description){
        Place source = placeRepo.findPlaceByPlaceId(sourceid);
        Place destination = placeRepo.findPlaceByPlaceId(destinationid);
        if(source==null || destination==null) return null;
        return journeyRepo.save(new Journey(source,destination,description));
    }

    public Map savetransport(int elementid,String name,String rent){
        Journey journey = journeyRepo.findJourneyByJourneyId(elementid);
        journey.addTransportList(transportRepo.save(new Transport(name,rent,true)));
        return showpage.getmodeledit("journey",journeyRepo.save(journey));
    }

    public Map deleteTransport(int elementid, int transportid){
        Journey journey = journeyRepo.findJourneyByJourneyId(elementid);
        journey.removeTransport(transportRepo.findTransportById(transportid));
        return showpage.getmodeledit("journey",journeyRepo.save(journey));
    }

    public Map getmodelhome(){
        return showpage.getmodelhome();
    }

    public Map getmodelerror(String msg){
        return showpage.getmodelerror(msg);
    }
}
