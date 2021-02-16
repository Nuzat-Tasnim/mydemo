package com.example.mydemo.BL;

import com.example.mydemo.BL.reusablecode.showPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class homeBL {
    @Autowired
    showPage showpage;

    public Map getmodelhome(){
        return showpage.getmodelhome();
    }
}
