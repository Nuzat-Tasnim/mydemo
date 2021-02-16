package com.example.mydemo.BL.builder;

import com.example.mydemo.DAL.model.Journey;
import com.example.mydemo.DAL.model.Place;

public class journeybuilder {
    public Journey build(Journey journey, Place source, Place destination, String description){
        journey.setSource(source);
        journey.setDestination(destination);
        journey.setDescription(description);
        return journey;
    }
}
