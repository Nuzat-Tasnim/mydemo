package com.example.mydemo.DAL.model;

import com.example.mydemo.DAL.interfaces.Iterator;
import com.example.mydemo.DAL.interfaces.IteratorContainer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookmarkedPlaces implements IteratorContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<IdContainer> placelist = new ArrayList<>();

    public List<IdContainer> getPlacelist() {
        return placelist;
    }

    public void setPlacelist(List<IdContainer> placelist) {
        this.placelist = placelist;
    }

    public void setUser() {
        this.user = (User) Session.getcurrentSession().getSession().getAttribute("user");;
    }

    public void add(IdContainer ic){
        placelist.add(ic);
    }
    public void remove(int index){
        placelist.remove(index);
    }

    @Override
    public Iterator getIterator() {
        return new bookmarkedplaceIterator();
    }

    public class bookmarkedplaceIterator implements Iterator{

        int index=0;

        @Override
        public Boolean hasNext() {
            if(index<placelist.size())
                return true;
            else
                return false;
        }

        @Override
        public IdContainer next() {
            return placelist.get(index++);
        }

        @Override
        public int getIndex(){
            return index;
        }
    }

}
