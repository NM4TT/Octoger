package com.nmatute.octoger.productmanagement.web.json;

public abstract class Request {
    public boolean isEmpty(String attribute){
        return attribute == null || attribute.equals("");
    }
}
