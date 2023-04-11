package com.nmatute.octoger.usermanagement.web.security.auth;

public abstract class Request {
    public boolean isEmpty(String attribute){
        return attribute == null || attribute.equals("");
    }
}
