package com.example.mydemo.DAL.model;

import javax.persistence.*;

@Entity
public class IdContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int key;
    private int id=0;
    private String element;

    public IdContainer() {
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public int getId() {
        return id;
    }

    public IdContainer(int id, String element) {
        this.id = id;
        this.element = element;
    }
    public IdContainer(int id) {
        this.id = id;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
}
