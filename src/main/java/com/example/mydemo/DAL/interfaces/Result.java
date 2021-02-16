package com.example.mydemo.DAL.interfaces;

import com.example.mydemo.DAL.model.Review;

import java.util.List;

public interface Result extends Cloneable{
    public List<Review> getReviewList();
    public void setReviewList(List<Review> reviewList);
    public void addReviewList(Review review);
    public Object clone();
}
