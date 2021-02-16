package com.example.mydemo.BL;

import com.example.mydemo.DAL.model.*;
import com.example.mydemo.DAL.journeyDAL;
import com.example.mydemo.DAL.placeDAL;
import com.example.mydemo.DAL.reviewDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class reviewBL {

    @Autowired
    placeDAL placeRepo;
    @Autowired
    journeyDAL journeyRepo;
    @Autowired
    reviewDAL reviewRepo;
    @Autowired
    bookmarkBL bookmarkBl;
    @Autowired
    showPage showpage;

    public User getuser(){
        return (User) Session.getcurrentSession().getSession().getAttribute("user");
    }

    public Map getmodelplace(int elementid, String text){
        Review review = reviewRepo.save(new Review(text,elementid, getuser()));
        Place place= placeRepo.findPlaceByPlaceId(elementid);
        if(!text.equals("")) place.addReviewList(review);
        place = placeRepo.save(place);
        boolean bookmark = bookmarkBl.getbookmarkplace(elementid);

        return showpage.getModel("place",place,bookmark);
    }
    public Map getmodeljourney(int elementid, String text){
        Review review = reviewRepo.save(new Review(text,elementid, getuser()));
        Journey journey = journeyRepo.findJourneyByJourneyId(elementid);
        if(!text.equals("")) journey.addReviewList(review);
        journey = journeyRepo.save(journey);
        boolean bookmark = bookmarkBl.getbookmarkjourney(elementid);

        return showpage.getModel("journey",journey,bookmark);
    }
}
