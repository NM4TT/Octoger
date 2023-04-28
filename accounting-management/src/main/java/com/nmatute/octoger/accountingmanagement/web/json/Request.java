package com.nmatute.octoger.accountingmanagement.web.json;

/**
 * Clase padre de Jsons
 * 
 * @author NM4TT
 */
public abstract class Request {
    public boolean isEmpty(String attribute){
        return attribute == null || attribute.equals("");
    }
}
