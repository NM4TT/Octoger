package com.nmatute.octoger.accountingmanagement.web.json;

public abstract class Request {
    public boolean isEmpty(String attribute){
        return attribute == null || attribute.equals("");
    }
}
