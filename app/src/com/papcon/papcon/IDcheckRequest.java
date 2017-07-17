package com.papcon.papcon;

/**
 * Created by Administrator on 2017-07-17.
 */


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qkrdb on 2017-07-09.
 */

public class IDcheckRequest extends StringRequest{

    final static private String URL = "http://dbals1630.cafe24.com/IDcheck.php";
    private Map<String, String> parameters;

    public IDcheckRequest(String userID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}