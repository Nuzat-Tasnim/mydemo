package com.example.mydemo.DAL.model;

import javax.persistence.*;

@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private boolean admin;
    private String userName;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private BookmarkedPlaces bookmarkedPlaces = new BookmarkedPlaces();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private BookmarkedJourneys bookmarkedJourneys = new BookmarkedJourneys();

    public User(){}

    public User(boolean admin, String userName, String email, String password) {
        this.admin = admin;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    //gettter setter

    public int getUserId() {
        return userId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BookmarkedPlaces getBookmarkedPlaces() {
        return bookmarkedPlaces;
    }

    public void setBookmarkedPlaces(BookmarkedPlaces bookmarkedPlaces) {
        this.bookmarkedPlaces = bookmarkedPlaces;
    }

    public BookmarkedJourneys getBookmarkedJourneys() {
        return bookmarkedJourneys;
    }

    public void setBookmarkedJourneys(BookmarkedJourneys bookmarkedJourneys) {
        this.bookmarkedJourneys = bookmarkedJourneys;
    }
}
