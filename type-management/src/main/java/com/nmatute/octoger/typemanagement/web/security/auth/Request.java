package com.nmatute.octoger.typemanagement.web.security.auth;

public abstract class Request {
    public boolean isEmpty(String attribute){
        return attribute == null || attribute.equals("");
    }
}
