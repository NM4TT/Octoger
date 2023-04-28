package com.nmatute.octoger.usermanagement.web.json;

/**
 * Clase para verificar empty en todos los JSON.
 * 
 * @author NM4TT
 */
public abstract class Request {
    public boolean isEmpty(String attribute){
        return attribute == null || attribute.equals("");
    }
}
