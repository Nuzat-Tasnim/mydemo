package com.example.mydemo.DAL;

import com.example.mydemo.DAL.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface placeDAL extends JpaRepository<Place, Integer> {

    public Place findPlaceByPlaceId(int id);
    public List<Place> findAllByPlaceNameContaining(String name);
}
