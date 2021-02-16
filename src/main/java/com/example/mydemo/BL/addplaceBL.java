package com.example.mydemo.BL;

import com.example.mydemo.DAL.model.Contacts;
import com.example.mydemo.DAL.model.Place;
import com.example.mydemo.DAL.model.Transport;
import com.example.mydemo.DAL.contactsDAL;
import com.example.mydemo.DAL.placeDAL;
import com.example.mydemo.DAL.transportDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class addplaceBL {
    @Autowired
    searchplaceBL searchplaceBl;
    @Autowired
    placeDAL placeRepo;
    @Autowired
    transportDAL transportRepo;
    @Autowired
    contactsDAL contactsRepo;
    @Autowired
    showPage showpage;

    public Map getsavedmodel(int elementid){
        return searchplaceBl.getmodel(elementid);
    }
    public Map getmodel(String placeName, String description){
        return showpage.getModel("place",placeRepo.save(new Place(placeName,description)),false);
    }
    public Map getmodeledit(int elementid){
        return showpage.getmodeledit("place",placeRepo.findPlaceByPlaceId(elementid));
    }

    public Map savehospcontact(int elementid,String name,String address,String contact){
        Place place = placeRepo.findPlaceByPlaceId(elementid);
        place.addHospcontacts(contactsRepo.save(new Contacts(name,address,contact)));
        return showpage.getmodeledit("place",placeRepo.save(place));
    }
    public Map deleteHospital(int elementid, int contactid){
        Place place = placeRepo.findPlaceByPlaceId(elementid);
        place.removeHospcontacts(contactsRepo.findContactsById(contactid));
        return showpage.getmodeledit("place",placeRepo.save(place));
    }
    public Map savepolicecontact(int elementid,String name,String address,String contact){
        Place place = placeRepo.findPlaceByPlaceId(elementid);
        place.addPolicecontacts(contactsRepo.save(new Contacts(name,address,contact)));
        return showpage.getmodeledit("place",placeRepo.save(place));
    }
    public Map deletePolice(int elementid, int contactid){
        Place place = placeRepo.findPlaceByPlaceId(elementid);
        place.removePolicecontacts(contactsRepo.findContactsById(contactid));
        return showpage.getmodeledit("place",placeRepo.save(place));
    }
    public Map savetransport(int elementid,String name,String availability){
        Place place = placeRepo.findPlaceByPlaceId(elementid);
        place.addTransport(transportRepo.save(new Transport(name,(availability!=null))));
        return showpage.getmodeledit("place",placeRepo.save(place));
    }
    public Map deleteTransport(int elementid, int transportid){
        Place place = placeRepo.findPlaceByPlaceId(elementid);
        place.removeTransport(transportRepo.findTransportById(transportid));
        return showpage.getmodeledit("place",placeRepo.save(place));
    }

    public Map getmodel(){
        return showpage.getmodelhome();
    }

    public Map getmodel(String msg){
        return showpage.getmodelerror(msg);
    }

}
