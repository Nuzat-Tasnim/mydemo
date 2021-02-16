package com.example.mydemo.DAL;

import com.example.mydemo.DAL.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface contactsDAL extends JpaRepository<Contacts, Integer> {
    public Contacts findContactsById(int contactid);
}
