package com.example.mydemo.DAL;

import com.example.mydemo.DAL.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface transportDAL extends JpaRepository<Transport, Integer> {
    public Transport findTransportById(int id);
}
