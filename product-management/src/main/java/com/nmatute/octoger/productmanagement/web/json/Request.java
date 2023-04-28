package com.nmatute.octoger.productmanagement.web.json;

/**
 * Clase padre para jsons
 * 
 * @author NM4TT
 */
public abstract class Request {
    public boolean isEmpty(String attribute){
        return attribute == null || attribute.equals("");
    }
}
