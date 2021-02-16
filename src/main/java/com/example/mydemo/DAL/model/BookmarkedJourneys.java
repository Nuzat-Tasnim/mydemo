package com.example.mydemo.DAL.model;

import com.example.mydemo.DAL.interfaces.Iterator;
import com.example.mydemo.DAL.interfaces.IteratorContainer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookmarkedJourneys implements IteratorContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
            //mappedBy = "user"
    )
    private List<IdContainer> journeylist = new ArrayList<>();

    public List<IdContainer> getJourneylist() {
        return journeylist;
    }

    public void setJourneylist(List<IdContainer> journeylist) {
        this.journeylist = journeylist;
    }

    public void setUser() {
        this.user = (User) Session.getcurrentSession().getSession().getAttribute("user");
    }

    public void add(IdContainer ic){
        journeylist.add(ic);
    }
    public void remove(int index){
        journeylist.remove(index);
    }


    @Override
    public Iterator getIterator() {
        return new bookmarkedjourneyIterator();
    }

    public class bookmarkedjourneyIterator implements Iterator {

        int index=0;

        @Override
        public Boolean hasNext() {
            if(index<journeylist.size())
                return true;
            else
                return false;
        }

        @Override
        public IdContainer next() {
            return journeylist.get(index++);
        }

        @Override
        public int getIndex(){
            return index;
        }
    }

}
