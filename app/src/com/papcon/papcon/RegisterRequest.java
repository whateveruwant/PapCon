package com.papcon.papcon;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qkrdb on 2017-07-09.
 */

public class RegisterRequest extends StringRequest{

    final static private String URL = "http://dbals1630.cafe24.com/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userAge", userAge + ""); // int형을 문자열로 변환하기 위해 + "" 해줌
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
