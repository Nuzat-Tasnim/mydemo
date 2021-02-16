package com.example.mydemo.DAL;

import com.example.mydemo.DAL.model.BookmarkedPlaces;
import com.example.mydemo.DAL.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bookmarkedplaceDAL extends JpaRepository<BookmarkedPlaces,Integer> {
    public BookmarkedPlaces findBookmarkedPlacesByUser(User user);
}
