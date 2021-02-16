package com.example.mydemo.DAL;

import com.example.mydemo.DAL.model.BookmarkedJourneys;
import com.example.mydemo.DAL.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bookmarkedjourneyDAL extends JpaRepository<BookmarkedJourneys, Integer> {
    public BookmarkedJourneys findBookmarkedJourneysByUser(User user);
}
