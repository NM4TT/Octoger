package com.nmatute.octoger.accountingmanagement.web.security.auth;

public abstract class Request {
    public boolean isEmpty(String attribute){
        return attribute == null || attribute.equals("");
    }
}
