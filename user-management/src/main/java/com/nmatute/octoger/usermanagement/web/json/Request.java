package com.nmatute.octoger.usermanagement.web.json;

public abstract class Request {
    public boolean isEmpty(String attribute){
        return attribute == null || attribute.equals("");
    }
}
