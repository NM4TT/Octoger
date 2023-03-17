package com.nmatute.octoger.usermanagement.Common;

public class Common {
    
    public static String exceptionDetail(StackTraceElement[] exceptionStack){
        String detail = "";

        for (StackTraceElement e : exceptionStack) {
            detail.concat(e.toString()).concat("\n");
        }

        return detail;
    }

}
