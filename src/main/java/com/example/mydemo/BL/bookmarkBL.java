package com.example.mydemo.BL;

import com.example.mydemo.DAL.interfaces.Iterator;
import com.example.mydemo.DAL.interfaces.Result;
import com.example.mydemo.DAL.model.IdContainer;
import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.DAL.*;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class bookmarkBL {

    Result result;

    @Autowired
    placeDAL placeRepo;
    @Autowired
    journeyDAL journeyRepo;
    @Autowired
    bookmarkedplaceDAL bookmarkedplaceRepo;
    @Autowired
    bookmarkedjourneyDAL bookmarkedjourneyRepo;
    @Autowired
    userDAL userRepo;
    @Autowired
    idcontainerDAL icRepo;
    @Autowired
    showPage showpage;

    public User getuser(){
        return (User) Session.getcurrentSession().getSession().getAttribute("user");
    }
    public void sessionactivity(User user){
        Session.getcurrentSession().getSession().removeAttribute("user");
        Session.getcurrentSession().getSession().setAttribute("user",user);
    }

    public boolean getbookmarkplace(int elementid){
        return -1 != iteration(getuser().getBookmarkedPlaces().getIterator(),elementid);
    }
    public boolean getbookmarkjourney(int elementid){
        return -1 != iteration(getuser().getBookmarkedJourneys().getIterator(),elementid);
    }

    public Map bookmarkplace(int elementid){
        User user = getuser();
        user.getBookmarkedPlaces().add(icRepo.save(new IdContainer(elementid)));
        user.setBookmarkedPlaces(bookmarkedplaceRepo.save(user.getBookmarkedPlaces()));

        sessionactivity(userRepo.save(user));
        result = placeRepo.findPlaceByPlaceId(elementid);

        return showpage.getModel("place",result, true);
    }
    public Map bookmarkjourney(int elementid){
        User user = getuser();
        user.getBookmarkedJourneys().add(icRepo.save(new IdContainer(elementid)));
        user.setBookmarkedJourneys(bookmarkedjourneyRepo.save(user.getBookmarkedJourneys()));

        sessionactivity(userRepo.save(user));
        result = journeyRepo.findJourneyByJourneyId(elementid);
        return showpage.getModel("journey",result, true);
    }

    public Map unbookmarkplace(int elementid){
        User user = getuser();
        user.getBookmarkedPlaces().remove(iteration(user.getBookmarkedPlaces().getIterator(),elementid)-1);
        sessionactivity(userRepo.save(user));
        result = placeRepo.findPlaceByPlaceId(elementid);
        return showpage.getModel("place",result, false);
    }
    public Map unbookmarkjourney(int elementid){
        User user = getuser();
        user.getBookmarkedJourneys().remove(iteration(user.getBookmarkedJourneys().getIterator(),elementid)-1);
        sessionactivity(userRepo.save(user));
        result = journeyRepo.findJourneyByJourneyId(elementid);
        return showpage.getModel("journey",result, false);
    }

    public int iteration(Iterator iterator,int elementid){
        while (iterator.hasNext()){
            if(iterator.next().getId()==elementid){
                return iterator.getIndex();
            }
        }
        return -1;
    }

}
