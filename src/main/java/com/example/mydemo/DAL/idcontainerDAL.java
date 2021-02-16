package com.example.mydemo.DAL;

import com.example.mydemo.DAL.model.IdContainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface idcontainerDAL extends JpaRepository<IdContainer, Integer> {

    IdContainer findIdContainerByElementAndId(String element, int id);
}
