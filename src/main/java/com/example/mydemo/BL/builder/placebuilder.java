package com.example.mydemo.BL.builder;

import com.example.mydemo.DAL.model.Place;

public class placebuilder {

    public Place build(Place place, String placeName, String description){
        place.setPlaceName(placeName);
        place.setDescription(description);
        return place;
    }

}
