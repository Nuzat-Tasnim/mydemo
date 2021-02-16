package com.example.mydemo.DAL.interfaces;

import com.example.mydemo.DAL.model.IdContainer;

public interface Iterator {
    public Boolean hasNext();
    public IdContainer next();
    public int getIndex();
}
