package com.example.mydemo.DAL.model;

import javax.servlet.http.HttpSession;

public class Session {
    private static HttpSession session = null;

    //singleton
    private static Session currentSession;
    public static Session getcurrentSession() {
        if(currentSession==null) return new Session();
        return currentSession;
    }
    public static void setcurrentSession(Session session){
        currentSession = session;
    }

    public static void setSession(HttpSession session) {
        currentSession.session = session;
    }

    public static HttpSession getSession() {
        return session;
    }
}
