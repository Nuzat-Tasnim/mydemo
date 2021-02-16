package com.example.mydemo.DAL;

import com.example.mydemo.DAL.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface reviewDAL extends JpaRepository<Review, Integer> {
}
