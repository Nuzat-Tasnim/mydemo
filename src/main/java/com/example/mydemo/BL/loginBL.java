package com.example.mydemo.BL;

import com.example.mydemo.DAL.model.BookmarkedJourneys;
import com.example.mydemo.DAL.model.BookmarkedPlaces;
import com.example.mydemo.DAL.model.Session;
import com.example.mydemo.DAL.model.User;
import com.example.mydemo.DAL.bookmarkedjourneyDAL;
import com.example.mydemo.DAL.bookmarkedplaceDAL;
import com.example.mydemo.DAL.userDAL;
import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class loginBL {
    @Autowired
    userDAL userRepo;
    @Autowired
    showPage showpage;
    @Autowired
    bookmarkedjourneyDAL bookmarkedjourneyRepo;
    @Autowired
    bookmarkedplaceDAL bookmarkedplaceRepo;

    public User loggingin(String email, String pass, HttpSession session){
        User user = verify(email,pass);

        if(user==null) return null;

        session.setAttribute("user",user);
        Session.setSession(session);

        user = init(user);
        System.out.println(user.getBookmarkedJourneys().getJourneylist());
        System.out.println(user.getBookmarkedPlaces().getPlacelist());
        return user;
    }

    public User verify(String email,String pass){
        User user = userRepo.findByEmail(email);
        if(user!=null){
            if(user.getPassword().equals(pass)){
                return user;
            }
        }
        return null;
    }

    public User init(User user){

        BookmarkedPlaces bp = bookmarkedplaceRepo.findBookmarkedPlacesByUser(user);
        if(bp==null) bp = new BookmarkedPlaces();
        bp.setUser();
        user.setBookmarkedPlaces(bp);

        BookmarkedJourneys bj = bookmarkedjourneyRepo.findBookmarkedJourneysByUser(user);
        if(bj==null) bj = new BookmarkedJourneys();
        bj.setUser();
        user.setBookmarkedJourneys(bj);

        return userRepo.save(user);
    }

    public Map getmodelhome(){
        return showpage.getmodelhome();
    }

    public void logout(){
        Session.getSession().invalidate();
        Session.setcurrentSession(null);
        Session.setSession(null);
    }
}
