package com.nmatute.octoger.productmanagement.web.security.auth;

public abstract class Request {
    public boolean isEmpty(String attribute){
        return attribute == null || attribute.equals("");
    }
}
