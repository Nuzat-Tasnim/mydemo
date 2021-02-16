package com.example.mydemo.DAL;

import com.example.mydemo.DAL.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface journeyDAL extends JpaRepository <Journey, Integer>{

    Journey findJourneyByJourneyId(int id);

    List<Journey> getAllBySource_PlaceNameAndDestination_PlaceName(String source, String destination);


}
